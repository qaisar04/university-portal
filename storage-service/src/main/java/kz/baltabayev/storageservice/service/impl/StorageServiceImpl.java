package kz.baltabayev.storageservice.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import kz.baltabayev.storageservice.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final AmazonS3 s3;

    public Bucket createBucket(String bucketName) {
        return listS3Buckets().stream()
                .filter(s -> s.getName().equals(bucketName))
                .findFirst()
                .orElseGet(() -> s3.createBucket(bucketName));
    }

    public List<Bucket> listS3Buckets() {
        return s3.listBuckets();
    }

    public void removeS3Bucket(String bucketName) {
        s3.listObjects(bucketName)
                .getObjectSummaries().stream()
                .filter(Objects::nonNull)
                .forEach(e -> s3.deleteObject(bucketName, e.getKey()));
        s3.deleteBucket(bucketName);
    }

    @Override
    public void uploadFile(String bucketName, String fileName, String filePath) {
        s3.putObject(bucketName, fileName, new File(filePath));
    }

    public List<String> listFiles(String bucketName) {
        ListObjectsV2Result listObjectsV2 = s3.listObjectsV2(bucketName);
        List<String> files = new ArrayList<>();
        for (S3ObjectSummary summary : listObjectsV2.getObjectSummaries()) {
            files.add(summary.getKey());
        }

        return files;
    }

    @Override
    public String deleteFile(String bucketName, String fileName) {
        s3.deleteObject(bucketName, fileName);
        return "%s removed successfully".formatted(fileName);
    }

    @Override
    @SneakyThrows
    public void downloadFile(String bucketName, String fileName, String downloadPath) {
        S3Object object = s3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = object.getObjectContent();
        FileUtils.copyInputStreamToFile(inputStream, new File(downloadPath));
    }
}
