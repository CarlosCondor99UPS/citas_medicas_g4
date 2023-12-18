package com.recursos.recursos.entity.impl;

import com.recursos.recursos.services.EmailSenderServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServicioImpl implements EmailSenderServicio {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String toUser, String subject, StringBuffer message) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("carlos.saico.mmc@gmail.com");
        mailMessage.setTo(toUser);
        mailMessage.setSubject(subject);
        mailMessage.setText(message.toString());

        javaMailSender.send(mailMessage);

    }



}