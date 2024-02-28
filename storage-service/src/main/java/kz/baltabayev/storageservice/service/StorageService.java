package kz.baltabayev.storageservice.service;

import com.amazonaws.services.s3.model.Bucket;

public interface StorageService {

    Bucket createBucket(String bucketName);

    void removeS3Bucket(String bucketName);

    void uploadFile(String bucketName, String fileName, String filePath);

    String deleteFile(String bucketName, String fileName);

    void downloadFile(String bucketName, String fileName, String downloadPath);
}
