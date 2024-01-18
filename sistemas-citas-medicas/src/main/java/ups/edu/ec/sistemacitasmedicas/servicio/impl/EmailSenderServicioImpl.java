package ups.edu.ec.sistemacitasmedicas.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ups.edu.ec.sistemacitasmedicas.servicio.EmailSenderServicio;


@Service
public class EmailSenderServicioImpl implements EmailSenderServicio {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String toUser, String subject, StringBuffer message) {

       SimpleMailMessage mailMessage = new SimpleMailMessage();

       mailMessage.setFrom("santiagocabrera354@gmail.com");
       mailMessage.setTo(toUser);
       mailMessage.setSubject(subject);
       mailMessage.setText(message.toString());

       javaMailSender.send(mailMessage);

    }



}
