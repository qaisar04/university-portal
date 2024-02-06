package kz.baltabayev.mailservice.service;


import kz.baltabayev.mailservice.dto.EmailMessageDto;
import kz.baltabayev.mailservice.enums.MailMessageType;

public interface MailService {

    void send(EmailMessageDto message, MailMessageType type);
}
