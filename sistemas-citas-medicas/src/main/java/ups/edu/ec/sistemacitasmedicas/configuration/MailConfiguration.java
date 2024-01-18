package ups.edu.ec.sistemacitasmedicas.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {


    @Bean
    public JavaMailSender getJavaMailSender(){

        JavaMailSender mailSender = new JavaMailSenderImpl();

        ((JavaMailSenderImpl) mailSender).setHost("smtp.gmail.com");// Host para GMAIL.
        ((JavaMailSenderImpl) mailSender).setPort(587);
        ((JavaMailSenderImpl) mailSender).setUsername("************************");
        ((JavaMailSenderImpl) mailSender).setPassword("***********");

        Properties props = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;

    }

}
