package com.org.ecom.email;

import com.org.ecom.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static com.org.ecom.email.EmailTemplates.ORDER_CONFIRMATION;
import static com.org.ecom.email.EmailTemplates.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(String destinationEmail, String customerName, BigDecimal amount, String orderReference) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
            messageHelper.setFrom("karthiklakshmi0797@gmail.com");
            final String templateName = PAYMENT_CONFIRMATION.getTemplateName();
            Map<String, Object> variables = Map.of(
                    "customerName", customerName,
                    "amount", amount,
                    "orderReference", orderReference
            );
            Context context = new Context();
            context.setVariables(variables);
            messageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

            sendEmail(messageHelper, templateName, context, destinationEmail);

        } catch (MessagingException e) {
            log.warn("Failed to send email to {}: {}", destinationEmail, e.getMessage());
        }
    }

    @Async
    public void sendOrderConfirmationEmail(String destinationEmail, String customerName, BigDecimal totalAmount, String orderReference, List<Product> products) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
            messageHelper.setFrom("karthiklakshmi0797@gmail.com");
            final String templateName = ORDER_CONFIRMATION.getTemplateName();
            Map<String, Object> variables = Map.of(
                    "customerName", customerName,
                    "totalAmount", totalAmount,
                    "orderReference", orderReference,
                    "products", products
            );
            Context context = new Context();
            context.setVariables(variables);
            messageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

            sendEmail(messageHelper, templateName, context, destinationEmail);

        } catch (MessagingException e) {
            log.warn("Failed to send email to {}: {}", destinationEmail, e.getMessage());
        }
    }

    private void sendEmail(MimeMessageHelper messageHelper, String templateName, Context context, String destinationEmail) {
        try {
            String html = templateEngine.process(templateName, context);
            messageHelper.setText(html, true);
            messageHelper.setTo(destinationEmail);
            emailSender.send(messageHelper.getMimeMessage());
            log.info("Email sent to {}", destinationEmail);
        } catch (MessagingException e) {
            log.warn("Failed to send email to {}: {}", destinationEmail, e.getMessage());
        }
    }

}
