package com.bata.billpunch.login.config; 


import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import org.springframework.stereotype.Component;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


@Component
public class SendMail implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public  void sentEmail(String email,String otp) throws IOException {
    // go to security on gmail turn on 2 stepm verification and generate app password and paste over real password 
    	//final String username = "XXXXXXXXXXXXXXXX";
        final String username = "mailjj070@gmail.com";
        
        final String password = "xuchajgeruqjybet";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
            message.setSubject("Required otp for authentication .");
            message.setText(otp);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}