package kz.baltabayev.mailservice.dto;

/**
 * This class represents the data transfer object for an email message.
 * It is a record, which is a special kind of class in Java that is used to model immutable data.
 * Each instance of this class contains an email and a message.
 */
public record EmailMessageDto(
        String email,
        String message
) {
}