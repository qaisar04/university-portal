package kz.baltabayev.storageservice.service;

import kz.baltabayev.storageservice.model.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * This interface represents a service for managing storage operations.
 * It provides methods for uploading, deleting, and downloading files, as well as extracting source and file name from a URL.
 */
public interface StorageService {

    /**
     * Uploads a file to a specified source.
     *
     * @param source the source to upload the file to.
     * @param target the target id.
     * @param file the file to upload.
     * @return an array of responses from the file upload operations.
     */
    FileUploadResponse[] uploadFile(String source, String target, List<MultipartFile> file);

    /**
     * Deletes a file from a specified source.
     *
     * @param source the source to delete the file from.
     * @param fileName the name of the file to delete.
     */
    void deleteFile(String source, String fileName);

    /**
     * Downloads a file from a specified source.
     *
     * @param source the source to download the file from.
     * @param fileName the name of the file to download.
     * @return the downloaded file as a byte array.
     */
    byte[] downloadFile(String source, String fileName);

    /**
     * Extracts the source and file name from a URL.
     *
     * @param url the URL to extract from.
     * @return an array containing the source and file name.
     */
    String[] extractSourceAndFileName(String url);
}