package com.example.jugalbeats.services;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.jugalbeats.utils.Utils;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    Logger logger = Logger.getLogger(EmailService.class);

    private final String smtpHost = "smtp.gmail.com";
    private final String smtpPort = "465";
    private final String mailFrom = "techtiders@gmail.com";

    public void sendEmail(String message, String subject, String mailTo) {

        Properties properties = System.getProperties();
        logger.info("PROPERTIES "+properties);

        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port",smtpPort);
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("booking.jugalbeats@gmail.com", "qwerty@123");
            }
        });

        session.setDebug(true);

        //compose the message [text,multi media]
        MimeMessage m = new MimeMessage(session);

        try {
            m.setFrom(mailFrom);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
            m.setSubject(subject);
            m.setText(message);
            Transport.send(m);
            logger.info("Mail sent successfully");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
