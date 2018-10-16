package com.whiuni.fastshop;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class MailSender {
	public static void sendMail(String toEmail) {
		/*
		 * https://developers.google.com/gmail/api/guides/sending
		 */
		
		//Setting up configurations for the email connection to the Google SMTP server using TLS
       Properties props = new Properties();
       props.put("mail.smtp.host", "true");
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.smtp.host", "smtp.gmail.com");
       props.put("mail.smtp.port", "587");
       props.put("mail.smtp.auth", "true");
       props.put("mail.debug", "true");
       props.put("mail.smtp.debug", "true");
        //Establishing a session with required user details
       Session session = Session.getInstance(props, new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication("EMAIL", "PASSWORD");
           }
       });
       try {
       	Message message = new MimeMessage(session);
       	
       	message.setFrom(new InternetAddress("EMAIL"));
       	
       	InternetAddress[] address = InternetAddress.parse(toEmail, true);
       	
			message.setRecipients(Message.RecipientType.TO, address);
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler No spam to my email, please!");
			
			Transport.send(message);
        } catch (MessagingException e) {
       	System.out.println("Unable to send an email" + e);
			throw new RuntimeException(e);
		}
       
	}
	
}