package com.ecommerce.buybuy.service.email;

import com.ecommerce.buybuy.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender emailSender;

    public void sendEmail(String to,
                                      String subject,
                                      String text
    ) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        emailSender.send(message);
    }

    public String generateVerificationCode() {
        return String.valueOf(new Random().nextInt(900000) + 100000); // Generate 6 digit code
    }

    public void sendVerificationEmail(User user) {
        String subject = "Account Verification";
        String verificationCode = "VERIFICATION CODE " + user.getVerificationCode();
        String htmlMessage = "<html><body style=\"font-family: Arial, sans-serif;\">" +
                "<div style=\"background-color: #f5f5f5; padding: 20px;\">" +
                "<h2 style=\"color: #333;\">Welcome to our app!</h2>" +
                "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>" +
                "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">" +
                "<h3 style=\"color: #333;\">Verification Code:</h3>" +
                "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>" +
                "</div></div></body></html>";

        try {
            sendEmail(user.getEmail(), subject, htmlMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send verification email", e);
        }
    }
}