package com.jedago.practica_dss.admysql.accessingdatamysql;

import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;


import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.InlinePicture;
import it.ozimov.springboot.mail.service.EmailService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
@Component
public class EmailServiceImpl implements EmailService {
	
	

    public void sendSimpleMessage( Retencion r, User u, JavaMailSender emailSender) {
    	
    	String msg = new String();
   	 	StringBuilder totalmsg = new StringBuilder();
   	
        
        SimpleMailMessage message = new SimpleMailMessage(); 
        //message.setFrom("cafejedago@gmail.com");
        message.setTo(u.getEmail()); 
        message.setSubject("Codigo de confirmacion:"+r.getId()); 
        message.setText(totalmsg.toString());
        emailSender.send(message);
        
    }
    
	@Override
	public MimeMessage send(Email mimeEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MimeMessage send(Email mimeEmail, String template, Map<String, Object> modelObject,
			InlinePicture... inlinePictures) throws CannotSendEmailException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
