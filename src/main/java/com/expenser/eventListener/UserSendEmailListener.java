package com.expenser.eventListener;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.expenser.api.LinkTokenService;
import com.expenser.enums.EmailType;
import com.expenser.enums.LinkTokenType;
import com.expenser.events.SendUserEmailEvent;
import com.expenser.model.UserDTO;
import com.expenser.repository.ProductConfigurationRepository;


@Component
public class UserSendEmailListener implements ApplicationListener<SendUserEmailEvent> {

	@Autowired
	LinkTokenService linkTokenService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private ProductConfigurationRepository configurationRepository;
	
	private static Logger logger = LogManager.getLogger(UserSendEmailListener.class);
	
	@Override
	public void onApplicationEvent(SendUserEmailEvent event) {
		String eventType = event.getEmailType();
		UserDTO user = event.getUser();
		if(eventType.equals(EmailType.REGISTRATION_CONFIRM.name())) {
			try {
			String tokenIdentifier= UUID.randomUUID().toString();
			linkTokenService.createUserLinkToken(tokenIdentifier, user.getUserIdentifier(), LinkTokenType.CONFIRM_REGISTRATION);
			
			String recipientAddress = user.getUsername();
			String subject = "Registration Confirmation";
			 String confirmationUrl 
	          = "#confirmAccount/tokenIdentifier/" + tokenIdentifier;
	        String message = "Please click on the link below to complete your registration process";
	        
	        SimpleMailMessage email = new SimpleMailMessage();
	        email.setTo(recipientAddress);
	        email.setSubject(subject);
	        String baseURL=configurationRepository.findValueByKey("BASE_URL");
	        email.setText(message + "\r\n" +baseURL+ confirmationUrl);
	        //comment for testing
//	        mailSender.send(email);
	        logger.error(email.getText());
			}catch(Exception e) {
				logger.error(e.getStackTrace().toString());
			}
		}
		if(eventType.equals(EmailType.RESET_PASSWORD.name())) {
			try {
			String tokenIdentifier= UUID.randomUUID().toString();
			linkTokenService.createUserLinkToken(tokenIdentifier, user.getUserIdentifier(), LinkTokenType.FORGET_PASSWORD);
			
			String recipientAddress = user.getUsername();
			String subject = "Password Reset";
			 String confirmationUrl 
	          = "#resetPassword/tokenIdentifier/" + tokenIdentifier;
	        String message = "Please click on the link below to reset your password";
	        
	        SimpleMailMessage email = new SimpleMailMessage();
	        email.setTo(recipientAddress);
	        email.setSubject(subject);
	        String baseURL=configurationRepository.findValueByKey("BASE_URL");
	        email.setText(message + "\r\n" +baseURL+ confirmationUrl);
	        //comment for testing
//	        mailSender.send(email);
	        logger.error(email.getText());
			}catch(Exception e) {
				logger.error(e.getStackTrace().toString());
			}
		}
	}

}
