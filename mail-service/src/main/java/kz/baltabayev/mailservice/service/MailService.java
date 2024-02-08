package kz.baltabayev.mailservice.service;

import kz.baltabayev.mailservice.dto.EmailMessageDto;
import kz.baltabayev.mailservice.enums.MailMessageType;

/**
 * MailService is an interface that provides a method for sending emails.
 * It is implemented by classes that provide concrete implementations for sending emails.
 */
public interface MailService {

    /**
     * Sends an email message.
     *
     * @param message The EmailMessageDto object containing the details of the email message to be sent.
     * @param type The MailMessageType enum value representing the type of the email message.
     */
    void send(EmailMessageDto message, MailMessageType type);
}