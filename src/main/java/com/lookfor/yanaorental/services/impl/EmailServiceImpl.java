package com.lookfor.yanaorental.services.impl;

import com.lookfor.yanaorental.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender emailSender;

    @Async
    @Override
    @SneakyThrows
    public void send(String recipientAddress, String subject, String text, boolean html) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setFrom(from);
        helper.setTo(recipientAddress);
        helper.setSubject(subject);
        helper.setText(text, true);
        emailSender.send(mimeMessage);
    }
}
