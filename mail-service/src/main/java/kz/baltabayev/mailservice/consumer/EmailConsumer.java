package kz.baltabayev.mailservice.consumer;

import kz.baltabayev.mailservice.dto.EmailMessageDto;
import kz.baltabayev.mailservice.enums.MailMessageType;
import kz.baltabayev.mailservice.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailConsumer {

    private final MailService mailService;

    @KafkaListener(topics = {"${spring.kafka.queues.greeting}"}, groupId = "mail-service")
    public void consumeNewsLetter(EmailMessageDto message) {
        log.info("Consuming email: {}", message);
        mailService.send(message, MailMessageType.GREETING);
    }
}
