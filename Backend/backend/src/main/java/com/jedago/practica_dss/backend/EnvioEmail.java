package com.jedago.practica_dss.backend;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.jedago.practica_dss.core.Order;
import com.jedago.practica_dss.core.OrderLine;

@Service
public class EnvioEmail {
	  //Importante hacer la inyección de dependencia de JavaMailSender:
    @Autowired
    private JavaMailSender mailSender;

    //Pasamos por parametro: destinatario, asunto y el mensaje
    public void sendEmail( Order order) {
    	 String msg = new String();
    	 StringBuilder totalmsg = new StringBuilder();
    	 for(OrderLine o: order.getProducts()) 
         {
         	msg = o.getProductName()+" "+o.getAmount()+"\n";
         	totalmsg.append(msg);
         }
        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo("cafejedago@gmail.com");
        email.setSubject("Orden: "+order.getId_order());
        
        String msg1 = new String();
        
        for(OrderLine o: order.getProducts()) 
        {
        	msg1.concat(o.getProductName()+" "+o.getAmount()+"\n");
        }
        
        email.setText(msg1);

        mailSender.send(email);
    }
   
}
