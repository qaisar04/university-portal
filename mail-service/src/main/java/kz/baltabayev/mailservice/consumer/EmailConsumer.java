package kz.baltabayev.mailservice.consumer;

import kz.baltabayev.mailservice.dto.EmailMessageDto;
import kz.baltabayev.mailservice.enums.MailMessageType;
import kz.baltabayev.mailservice.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for consuming messages from Kafka topics.
 * It uses the MailService to send emails based on the consumed messages.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class EmailConsumer {

    // The MailService is used to send emails.
    private final MailService mailService;

    /**
     * This method consumes messages from the Kafka topic defined in the application properties.
     * It logs the consumed message and uses the MailService to send an email.
     * The type of the email is defined as GREETING.
     *
     * @param message the consumed message from the Kafka topic.
     */
    @KafkaListener(topics = {"${spring.kafka.queues.greeting}"}, groupId = "mail-service")
    public void consumeNewsLetter(EmailMessageDto message) {
        log.info("Consuming email: {}", message);
        mailService.send(message, MailMessageType.GREETING);
    }
}