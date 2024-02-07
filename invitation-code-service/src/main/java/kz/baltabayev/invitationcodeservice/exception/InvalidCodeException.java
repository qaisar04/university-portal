package kz.baltabayev.invitationcodeservice.exception;

public class InvalidCodeException extends RuntimeException {
    public InvalidCodeException(String message) {
        super("Invalid invite code: " + message);
    }
}
