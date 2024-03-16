package kz.baltabayev.storageservice.service;

import kz.baltabayev.storageservice.model.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {

    FileUploadResponse[] uploadFile(String source, Long target, List<MultipartFile> file);

    void deleteFile(String source, String fileName);

    byte[] downloadFile(String source, String fileName);

    void deleteFileUsingUrl(String url);
}
