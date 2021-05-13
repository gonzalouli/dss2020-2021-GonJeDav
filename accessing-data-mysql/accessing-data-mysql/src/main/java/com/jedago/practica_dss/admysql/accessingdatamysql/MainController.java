package com.jedago.practica_dss.admysql.accessingdatamysql;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	

	@Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        
        mailSender.setUsername("cafejedago@gmail.com");
        mailSender.setPassword("ujuxmjliojqxlaqh");
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
       
        return mailSender;
    }
	
@Autowired
   private JavaMailSender emailSender;

  @Autowired // This means to get the bean called userRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private UserRepository userRepository;
  @Autowired
  private TransaccionRepository transRepository;
  @Autowired
  private RetencionRepository retenRepository;
  @PostMapping(path="/add") // Map ONLY POST Requests
  public String addNewUser (@RequestParam String firstName, @RequestParam String lastName
      , @RequestParam String email) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    //User n = new User(firstName, lastName,email);
	User n = new User();
    n.setFirstName(firstName);
    n.setLastName(lastName);
    n.setEmail(email);
    userRepository.save(n);
    return "Saved with " + n.getId();
  }

  @GetMapping(path="/all")
  public List<User> getAllUsers() {
    // This returns a JSON or XML with the users
    return (List<User>) userRepository.findAll();
  }
  
  @GetMapping(path="/user")
  public Optional<User> getUser(@RequestParam String id) {
    return userRepository.findById(id);
  }
  
  @GetMapping(path="/user/saldo")
  public Optional<BigDecimal> getUserBalance(@RequestParam String id) {
	  Optional<BigDecimal> saldo = Optional.empty();
	  if(userRepository.findById(id).isPresent())
		  saldo = Optional.of(userRepository.findById(id).get().getSaldo());
	  return saldo;
  }
  
  @PatchMapping(path="/user/saldo")
  public String addUserBalance(@RequestParam String id, @RequestParam BigDecimal cash) {
	  User u = null;
	  if(userRepository.findById(id).isPresent())
	  {
		  u = userRepository.findById(id).get();
		  u.ingreso(cash);
		  
		  userRepository.save(u);
		  
		  return "El saldo del usuario ahora es de " + u.getSaldo().toString();
	  }
	  else
	  {
		  return "No se ha encontrado el usuario";
	  }
  }
//  
//  @PatchMapping(path="/user/pago")
//  public String addUserPayment(@RequestParam String id, @RequestParam BigDecimal cash, @RequestParam String concepto) {
//	  User u = null;
//	  if(userRepository.findById(id).isPresent())
//	  {
//		  u = userRepository.findById(id).get();
//		  if(u.getSaldo().compareTo(cash) >= 0)
//		  {
//			  u.pago(cash);
//			  Transaccion t = new Transaccion(u, concepto, LocalDateTime.now(), cash);
//			  u.addTransaccion(t);
//			  userRepository.save(u);
//			  transRepository.save(t);
//			  return "El pago se ha realizado correctamente ";
//		  }
//		  else
//		  {
//			  return "No hay suficiente saldo para el pago";
//		  }
//	  }
//	  else
//	  {
//		  return "No se ha encontrado el usuario";
//	  }
//  }
  

  @PostMapping(path="/user/pago")
  public String addUserPaymentToConfirm(@RequestParam String id, @RequestParam BigDecimal cash, @RequestParam String concepto) {
	  Optional<User> u = userRepository.findById(id);
	  
	  if(u.isPresent())
	  {
		  if(u.get().getSaldo().compareTo(cash) >= 0)
		  {
			  Transaccion t = new Transaccion(u.get(), concepto, LocalDateTime.now(), cash);
			  Retencion r = new Retencion(t);
			  u.get().addRetencion(r);
			  EmailServiceImpl mail = new EmailServiceImpl();
			  mail.sendSimpleMessage(r, u.get(), emailSender);
			  return "Se ha enviado un codigo de verificacion a su email, introduzcalo en /payment/code";
		  }
		  else
		  {
			  return "No hay suficiente saldo para el pago";
		  }
	  }
	  else
	  {
		  return "No se ha encontrado el usuario";
	  }
  }
  
  @PatchMapping(path="/user/payment/code")
  public String confirmTransaction(@RequestParam String idVerificacion, @RequestParam String idUser) {
	  User u = null;
	  if(retenRepository.findById(idVerificacion).isPresent()) {
		  if(retenRepository.findById(idVerificacion).get().getTransaccionRetenida().getUsuario().getId().equals(idUser))
	  		{
			  Retencion r = retenRepository.findById(idVerificacion).get();
			  u = userRepository.findById(r.getTransaccionRetenida().getUsuario().getId()).get();
			  u.retiro(r.getTransaccionRetenida().getPrice());
			  u.addTransaccion(r.getTransaccionRetenida());
			  transRepository.save(r.getTransaccionRetenida());
			  userRepository.save(u);
			  retenRepository.delete(r);
			  return "Se ha realizado el pago: id-"+r.getId()+"-- "+r.getTransaccionRetenida().getUsuario()+" euros";
	  		}
	  		else
	  			return "Usuario erroneo";
	  }else
		  return "Codigo incorrecto";
  }
  
 }

  
