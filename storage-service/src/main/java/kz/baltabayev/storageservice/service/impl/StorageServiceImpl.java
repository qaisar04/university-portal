package kz.baltabayev.storageservice.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import kz.baltabayev.storageservice.exception.FileDownloadException;
import kz.baltabayev.storageservice.exception.InvalidFileTypeException;
import kz.baltabayev.storageservice.exception.InvalidUrlException;
import kz.baltabayev.storageservice.model.dto.FileUploadResponse;
import kz.baltabayev.storageservice.model.entity.S3File;
import kz.baltabayev.storageservice.model.types.ContentSource;
import kz.baltabayev.storageservice.service.S3FileService;
import kz.baltabayev.storageservice.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static kz.baltabayev.storageservice.model.types.ContentSource.*;


/**
 * This class implements the StorageService interface.
 * It is annotated with @Service, indicating that it is a Spring service.
 * It uses Lombok's @RequiredArgsConstructor to automatically generate a constructor with required fields.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    /**
     * The Amazon S3 client.
     */
    private final AmazonS3 s3;

    /**
     * The service for managing S3File entities.
     */
    private final S3FileService s3FileService;

    /**
     * Creates a new S3 bucket with the specified name, or returns an existing one.
     *
     * @param bucketName the name of the bucket.
     * @return the created or existing bucket.
     */
    public Bucket createBucket(String bucketName) {
        return listS3Buckets().stream()
                .filter(s -> StringUtils.equals(s.getName(), bucketName))
                .findFirst()
                .orElseGet(() -> s3.createBucket(bucketName));
    }

    /**
     * Deletes an S3 bucket with the specified name.
     *
     * @param bucketName the name of the bucket.
     */
    public void removeS3Bucket(String bucketName) {
        s3.listObjects(bucketName)
                .getObjectSummaries().stream()
                .filter(Objects::nonNull)
                .forEach(e -> s3.deleteObject(bucketName, e.getKey()));
        s3.deleteBucket(bucketName);
    }

    /**
     * Lists all S3 buckets.
     *
     * @return a list of all S3 buckets.
     */
    public List<Bucket> listS3Buckets() {
        return s3.listBuckets();
    }

    /**
     * Uploads a list of files to an S3 bucket.
     *
     * @param source the source of the file content.
     * @param id the id of the target.
     * @param files the files to upload.
     * @return an array of responses from the file upload operations.
     */
    @Override
    public FileUploadResponse[] uploadFile(String source, String id, List<MultipartFile> files) {
        ContentSource contentSource = valueOf(source.toUpperCase());
        String bucketName = contentSource.getBucketName();

        if (contentSource == USER_PROFILE_IMAGE) {
            for (MultipartFile file : files) {
                if (!isImageFile(file)) {
                    throw new InvalidFileTypeException("image");
                }
            }
        }

        createBucket(bucketName);

        List<FileUploadResponse> responses = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            File fileToUpload = convertMultiPartFileToFile(multipartFile);
            String filename = UUID.randomUUID().toString().replace("-", "");

            s3.putObject(bucketName, filename, fileToUpload);

            if (!fileToUpload.delete()) {
                log.error("Could not delete temporary file {}", fileToUpload.getAbsolutePath());
            }

            s3FileService.save(S3File.builder()
                    .fileName(filename)
                    .source(contentSource)
                    .target(id)
                    .build());

            responses.add(new FileUploadResponse(filename, source, s3.getUrl(bucketName, filename).toString()));
        }

        return responses.toArray(new FileUploadResponse[0]);
    }

    /**
     * Checks if a file is an image file.
     *
     * @param file the file to check.
     * @return true if the file is an image file, false otherwise.
     */
    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    /**
     * Lists all files in an S3 bucket.
     *
     * @param bucketName the name of the bucket.
     * @return a list of all files in the bucket.
     */
    public List<String> listFiles(String bucketName) {
        ListObjectsV2Result listObjectsV2 = s3.listObjectsV2(bucketName);
        return listObjectsV2.getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Deletes a file from an S3 bucket.
     *
     * @param source the source of the file content.
     * @param fileName the name of the file.
     */
    @Override
    public void deleteFile(String source, String fileName) {
        ContentSource contentSource = valueOf(source.toUpperCase());
        String bucketName = contentSource.getBucketName();
        Optional<S3File> s3File = s3FileService.getByFileNameAndSource(fileName, contentSource);
        s3File.ifPresent(file -> s3FileService.delete(file.getId()));
        s3.deleteObject(bucketName, fileName);
    }

    /**
     * Extracts the source and file name from a URL.
     *
     * @param url the URL to extract from.
     * @return an array containing the source and file name.
     */
    @Override
    public String[] extractSourceAndFileName(String url) {
        try {
            URL fileUrl = new URL(url);
            String host = fileUrl.getHost();
            String bucketName = host.substring(0, host.indexOf('.'));
            String fileName = fileUrl.getPath().substring(1);
            return new String[]{fromBucketName(bucketName).toString(), fileName};
        } catch (MalformedURLException e) {
            throw new InvalidUrlException("Invalid URL: " + e);
        }
    }

    /**
     * Downloads a file from an S3 bucket.
     *
     * @param source the source of the file content.
     * @param fileName the name of the file.
     * @return the downloaded file as a byte array.
     */
    @Override
    public byte[] downloadFile(String source, String fileName) {
        ContentSource contentSource = valueOf(source.toUpperCase());
        S3Object object = s3.getObject(contentSource.getBucketName(), fileName);
        try (S3ObjectInputStream inputStream = object.getObjectContent()) {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            log.error("Error downloading file", e);
            throw new FileDownloadException(e.getMessage());
        }
    }


    /**
     * Converts a MultipartFile to a File.
     *
     * @param file the MultipartFile to convert.
     * @return the converted File.
     */
    public static File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
            throw new RuntimeException("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
