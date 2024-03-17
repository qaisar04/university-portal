package kz.baltabayev.storageservice.exception;

/**
 * This class represents a custom exception that is thrown when an invalid file type is encountered.
 * It extends the RuntimeException class, so it is an unchecked exception.
 */
public class InvalidFileTypeException extends RuntimeException {

    /**
     * Constructs a new InvalidFileTypeException with a detailed message.
     *
     * @param fileType the type of the file that caused the exception, saved for later retrieval by the Throwable.getMessage() method.
     */
    public InvalidFileTypeException(String fileType) {
        super("Only %s files are allowed!".formatted(fileType));
    }
}