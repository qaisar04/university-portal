package kz.baltabayev.invitationcodeservice.exception;

/**
 * Exception thrown when an invalid invite code is provided.
 */
public class InvalidCodeException extends RuntimeException {

    /**
     * Constructs a new InvalidCodeException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidCodeException(String message) {
        super("Invalid invite code: " + message);
    }
}