package kz.baltabayev.storageservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String uploadFile(String source, Long id, MultipartFile file);

    void deleteFile(String bucketName, String fileName);

    byte[] downloadFile(String bucketName, String fileName);

}
