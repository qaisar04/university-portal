package kz.baltabayev.storageservice.exception;

/**
 * This class represents a custom exception that is thrown when there is an error downloading a file.
 * It extends the RuntimeException class, so it is an unchecked exception.
 */
public class FileDownloadException extends RuntimeException {

    /**
     * Constructs a new FileDownloadException with a detailed message.
     *
     * @param message the detail message, saved for later retrieval by the Throwable.getMessage() method.
     */
    public FileDownloadException(String message) {
        super("Error downloading file %s".formatted(message));
    }
}