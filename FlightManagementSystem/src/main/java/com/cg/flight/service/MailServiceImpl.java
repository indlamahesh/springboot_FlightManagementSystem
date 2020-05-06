package com.cg.flight.service;


import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.cg.flight.util.MailConstants;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailServiceImpl  implements MailService{

	
	Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	@Qualifier("mailprops")
	private Properties props;

	@Value("${fromAddress}")
	private String fromAddress;

	@Value("${toAddress}")
	private String reciepentAddress;

	@Value("${pwd}")
	private String password;

	@Override
	public String sendMail() {
		try {
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromAddress, password);
				}
			});

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromAddress));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(reciepentAddress));
			message.setSubject("Project Review Meeting");
			message.setText("Today will be the meeeting at 11:00AM- message sent from java code");
			Transport.send(message);
			logger.info(MailConstants.MAIL_SUCCESS);
			return MailConstants.MAIL_SUCCESS;
		} catch (MessagingException ex) {
			logger.error(ex.getMessage());
			return MailConstants.MAIL_FAILURE;
		}

	}

}

