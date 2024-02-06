package kz.baltabayev.mailservice.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MailMessageType {

    GREETING("Greeting", "greeting.ftl");

    private final String subject;
    private final String template;
}
