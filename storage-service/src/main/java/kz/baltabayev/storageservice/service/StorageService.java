package kz.baltabayev.storageservice.service;

import kz.baltabayev.storageservice.model.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    FileUploadResponse uploadFile(String source, Long target, MultipartFile file);

    void deleteFile(String source, String fileName);

    byte[] downloadFile(String source, String fileName);

}
