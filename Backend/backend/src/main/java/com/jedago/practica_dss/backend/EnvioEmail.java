package com.jedago.practica_dss.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo("cafejedago@gmail.com");
        email.setSubject("Orden: "+order.getId_order());
        
        String msg = new String();
        
        for(OrderLine o: order.getProducts()) 
        {
        	msg.concat(o.getProductName()+" "+o.getAmount()+"\n");
        }
        
        email.setText(msg);

        mailSender.send(email);
    }
    
    
    
    
}
