package kz.baltabayev.mailservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing different types of mail messages.
 * Each type of mail message has a subject and a template associated with it.
 */
@Getter
@AllArgsConstructor
public enum MailMessageType {

    /**
     * GREETING type of mail message.
     * It has a subject "Greeting" and uses the "greeting.ftl" template for the mail content.
     */
    GREETING("Greeting", "greeting.ftl");

    /**
     * The subject of the mail message.
     */
    private final String subject;

    /**
     * The name of the template to be used for the mail content.
     */
    private final String template;
}