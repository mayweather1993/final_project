package com.homework.project.services;

import com.homework.project.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;

import static com.homework.project.GeneralConstants.RECEIPT_TEMPLATE;

@Service
@Slf4j
@AllArgsConstructor
public class MailService {

    private final ApplicationProperties properties;
    private final JavaMailSender javaMailSender;
    private final TemplateEngineService templateEngine;

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
            isMultipart, isHtml, to, subject, content);

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, "UTF-8");
            message.setTo(to);
            message.setFrom(properties.getFrom());
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Sent email to User '{}'", to);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.warn("Email could not be sent to user '{}'", to, e);
            } else {
                log.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
            }
        }
    }

    @Async
    public void sendEmailFromTemplate(String emailTo, String templateName, String title, List<String> parameters) {
        String content = templateEngine.generateMessage(templateName, parameters);
        sendEmail(emailTo, title, content, false, false);
    }

    @Async
    public void sendReceiptEmail(final String emailTo, final List<String> parameters) {
        sendEmailFromTemplate(emailTo, RECEIPT_TEMPLATE, "Your Receipt was generated", parameters);
    }

}
