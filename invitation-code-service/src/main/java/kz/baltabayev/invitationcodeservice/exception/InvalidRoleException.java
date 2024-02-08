package kz.baltabayev.invitationcodeservice.exception;

/**
 * Exception thrown when an invalid role is provided.
 */
public class InvalidRoleException extends IllegalArgumentException {

    /**
     * Constructs a new InvalidRoleException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidRoleException(String message) {
        super(message);
    }
}