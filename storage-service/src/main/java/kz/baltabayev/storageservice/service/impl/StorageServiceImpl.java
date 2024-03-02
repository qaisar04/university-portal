package kz.baltabayev.storageservice.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import kz.baltabayev.storageservice.model.entity.S3File;
import kz.baltabayev.storageservice.model.types.ContentSource;
import kz.baltabayev.storageservice.service.S3FileService;
import kz.baltabayev.storageservice.service.StorageService;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final AmazonS3 s3;
    private final S3FileService s3FileService;

    public Bucket createBucket(String bucketName) {
        return listS3Buckets().stream()
                .filter(s -> s.getName().equals(bucketName))
                .findFirst()
                .orElseGet(() -> s3.createBucket(bucketName));
    }

    public void removeS3Bucket(String bucketName) {
        s3.listObjects(bucketName)
                .getObjectSummaries().stream()
                .filter(Objects::nonNull)
                .forEach(e -> s3.deleteObject(bucketName, e.getKey()));
        s3.deleteBucket(bucketName);
    }

    public List<Bucket> listS3Buckets() {
        return s3.listBuckets();
    }

    @Override
    public String uploadFile(String source, Long id, MultipartFile file) {
        String bucketName = ContentSource.valueOf(source.toUpperCase()).getBucketName();
        String filename = file.getOriginalFilename();

        var fileToUpload = convertMultiPartFileToFile(file);
        s3.putObject(bucketName, filename, fileToUpload);

        s3FileService.save(S3File.builder()
                .fileName(filename)
                .source(ContentSource.valueOf(source.toUpperCase()))
                .target(id)
                .build());

        return s3.getUrl(bucketName, filename).toString();
    }

    //todo: use it in the future for logic
    public List<String> listFiles(String bucketName) {
        ListObjectsV2Result listObjectsV2 = s3.listObjectsV2(bucketName);
        List<String> files = new ArrayList<>();
        for (S3ObjectSummary summary : listObjectsV2.getObjectSummaries()) {
            files.add(summary.getKey());
        }

        return files;
    }

    @Override
    public void deleteFile(String source, String fileName) {
        ContentSource contentSource = ContentSource.valueOf(source.toUpperCase());
        String bucketName = contentSource.getBucketName();
        Optional<S3File> s3File = s3FileService.getByFileNameAndSource(fileName, contentSource);
        s3File.ifPresent(file -> s3FileService.delete(file.getId()));
        s3.deleteObject(bucketName, fileName);
    }

    @Override
    @SneakyThrows
    public byte[] downloadFile(String source, String fileName) {
        ContentSource contentSource = ContentSource.valueOf(source.toUpperCase());
        S3Object object = s3.getObject(contentSource.getBucketName(), fileName);
        @Cleanup S3ObjectInputStream inputStream = object.getObjectContent();
        return IOUtils.toByteArray(inputStream);
    }

    public static File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
