package kz.baltabayev.mailservice.service.impl;

import freemarker.template.Configuration;
import kz.baltabayev.mailservice.dto.EmailMessageDto;
import kz.baltabayev.mailservice.enums.MailMessageType;
import kz.baltabayev.mailservice.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * MailServiceImpl is a class that provides a concrete implementation of the MailService interface.
 * It uses the JavaMailSender and FreeMarker's Configuration for sending emails.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    /**
     * JavaMailSender for sending emails.
     */
    private final JavaMailSender mailSender;

    /**
     * FreeMarker's Configuration for processing templates into strings.
     */
    private final Configuration ftl;

    /**
     * The email of the sender.
     */
    @Value("${spring.mail.sender.email}")
    private String sender;

    /**
     * Sends an email message.
     *
     * @param message The EmailMessageDto object containing the details of the email message to be sent.
     * @param type The MailMessageType enum value representing the type of the email message.
     */
    @Override
    public void send(EmailMessageDto message, MailMessageType type) {
        var msg = mailSender.createMimeMessage();

        try {
            var helper = new MimeMessageHelper(
                    msg,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name()
            );

            var template = ftl.getTemplate(type.getTemplate());
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, Map.of(
                    "content", message.message(),
                    "subject", type.getSubject()
            ));

            helper.setText(html, true);
            helper.setTo(message.email());
            helper.setFrom(sender);
            helper.setSubject(type.getSubject());

            mailSender.send(msg);

            log.info("Mail Sent Successfully {}", message);
        } catch (Exception e) {
            log.error("Error while Sending Mail, msg {}", e.getMessage());
        }
    }
}