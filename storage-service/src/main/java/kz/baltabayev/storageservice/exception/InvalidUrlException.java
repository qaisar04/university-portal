package kz.baltabayev.storageservice.exception;

/**
 * This class represents a custom exception that is thrown when an invalid URL is encountered.
 * It extends the RuntimeException class, so it is an unchecked exception.
 */
public class InvalidUrlException extends RuntimeException {

    /**
     * Constructs a new InvalidUrlException with a detailed message.
     *
     * @param message the detail message, saved for later retrieval by the Throwable.getMessage() method.
     */
    public InvalidUrlException(String message) {
        super(message);
    }
}