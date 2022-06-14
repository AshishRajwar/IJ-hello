package com.example.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


public interface EmailSenderService {
    public void sendSimpleEmail(String toEmail, String body, String subject);
}
