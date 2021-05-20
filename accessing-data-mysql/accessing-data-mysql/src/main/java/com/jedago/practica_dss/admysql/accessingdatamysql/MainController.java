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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // This means that this class is a Controller
@RequestMapping(path="/gonjepago") // This means URL's start with /demo (after Application path)
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
  
  //Ejemplo: curl -X POST localhost:8080/gonjepago/user -d firstName=nombre -d lastName=apellidos -d email=mail

  @PostMapping(path="/user") // Map ONLY POST Requests
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
  
  //Ejemplo: curl -X GET localhost:8080/gonjepago/all
  @GetMapping(path="/all")
  public List<User> getAllUsers() {
    // This returns a JSON or XML with the users
    return (List<User>) userRepository.findAll();
  }
  
  //Ejemplo: curl -X GET localhost:8080/gonjepago/user -d 297e77017966a10a017966a2a5580000

  @GetMapping(path="/user")
  public Optional<User> getUser(@RequestBody String id) {
	  //Como solo hay un parametro no hay que colocar el id= en el curl
    return userRepository.findById(id);
  }
  
  //Ejemplo: curl -X GET localhost:8080/gonjepago/user/cash -d 297e77017966a10a017966a2a5580000
  @GetMapping(path="/user/cash")
  public Optional<BigDecimal> getUserBalance(@RequestBody String id) {
	  Optional<BigDecimal> saldo = Optional.empty();
	  if(userRepository.findById(id).isPresent())
		  saldo = Optional.of(userRepository.findById(id).get().getSaldo());
	  return saldo;
  }
  
  //Ejemplo: curl -X PATCH localhost:8080/gonjepago/user/cash -d id="297e77017966a10a017966a2a5580000" -d cash=40
  @PatchMapping(path="/user/cash")
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
  //Ejemplo: curl -X POST localhost:8080/gonjepago/user/payment -d id=297e77017966a10a017966a2a5580000 -d cash=1000 -d concepto="televisor 4k"
  //No abusar del uso de comandos ya que hay un correo real asociado
  @PostMapping(path="/user/payment")
  public String addUserPaymentToConfirm(@RequestParam String id, @RequestParam BigDecimal cash, @RequestParam String concepto) {
	  Optional<User> u = userRepository.findById(id);
	  
	  if(u.isPresent())
	  {
		  if(u.get().getSaldo().compareTo(cash) >= 0)
		  {
			  Transaccion t = new Transaccion(u.get(), concepto, LocalDateTime.now(), cash);
			  transRepository.save(t);
			  EmailServiceImpl mail = new EmailServiceImpl();
			  mail.sendSimpleMessage(t, u.get(), emailSender);
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
  
  //Ejemplo: curl -X PATCH localhost:8080/gonjepago/user/payment/code -d idVerificacion=297e77017989446e01798948d9c10000 -d idUser=297e77017966a10a017966a2a5580000
  @PatchMapping(path="/user/payment/code")
  public String confirmTransaction(@RequestParam String idVerificacion, @RequestParam String idUser) {
	  User u = null;
	  Optional<Transaccion> t = transRepository.findById(idVerificacion);
	  if(t.isPresent()) {
		  if(t.get().getUsuario().getId().equals(idUser))
	  		{
			  //transRepository.deleteById(idVerificacion);
			  t.get().setConfirmado(true);
			  t.get().updateDate();
			  u = t.get().getUsuario();
			  //u = userRepository.findById(t.get().getUsuario().getId()).get();
			  u.retiro( t.get().getPrice()); 
			  u.addTransaccion(t.get());

			  //transRepository.save(t.get());
			  userRepository.save(u);
			  return "Se ha realizado el pago: id-"+t.get().getId()+"-- "+t.get().getPrice()+" euros";
	  		}
	  		else
	  			return "Usuario erroneo";
	  }else
		  return "Codigo incorrecto";
  }
  
 }

  
