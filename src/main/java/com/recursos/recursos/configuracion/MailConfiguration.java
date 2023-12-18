package com.recursos.recursos.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class MailConfiguration {
    @Bean
    public JavaMailSender getJavaMailSender(){

        JavaMailSender mailSender = new JavaMailSenderImpl();

        ((JavaMailSenderImpl) mailSender).setHost("smtp.gmail.com");// Host para GMAIL.
        ((JavaMailSenderImpl) mailSender).setPort(587);
        ((JavaMailSenderImpl) mailSender).setUsername("carlos.saico.mmc@gmail.com");
        ((JavaMailSenderImpl) mailSender).setPassword("npyybmuaxirqkvwe");

        Properties props = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;

    }

}