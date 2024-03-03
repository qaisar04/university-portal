package kz.baltabayev.storageservice.exception;

public class InvalidFileTypeException extends RuntimeException {
    public InvalidFileTypeException(String fileType) {
        super("Only %s files are allowed!".formatted(fileType));
    }
}
