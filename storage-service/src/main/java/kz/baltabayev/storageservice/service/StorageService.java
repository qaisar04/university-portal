package kz.baltabayev.storageservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String uploadFile(String source, Long target, MultipartFile file);

    void deleteFile(String source, String fileName);

    byte[] downloadFile(String source, String fileName);

}
