package kz.baltabayev.storageservice.service;

import kz.baltabayev.storageservice.model.types.ContentSource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String uploadFile(String source, Long id, MultipartFile file);

    String deleteFile(String bucketName, String fileName);

    void downloadFile(String bucketName, String fileName, String downloadPath);

}
