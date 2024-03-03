package kz.baltabayev.storageservice.exception;

public class FileDownloadException extends RuntimeException {
    public FileDownloadException(String message) {
        super("Error downloading file %s".formatted(message));
    }
}
