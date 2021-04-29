package com.jedago.practica_dss.backend;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jedago.practica_dss.core.Cafe;
import com.jedago.practica_dss.core.CashBox;
import com.jedago.practica_dss.core.Order;
import com.jedago.practica_dss.core.OrderLine;
import com.jedago.practica_dss.core.Product;
import com.jedago.practica_dss.core.ProductType;
import com.jedago.practica_dss.core.User;
import com.jedago.practica_dss.persistance.OrdersRepositoryByFile;
import com.jedago.practica_dss.persistance.ProductsRepositoryByFile;
import com.jedago.practica_dss.persistance.UsersRepositoryByFile;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@SpringBootApplication
@RestController
public class BackendApplication {
	
	private static OrdersRepositoryByFile or;
	private static ProductsRepositoryByFile pr;
	private static UsersRepositoryByFile ur;
	private static Cafe cafe;
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
	
	public static void main(String[] args) throws Exception {
		
		or = new OrdersRepositoryByFile();
		pr = new ProductsRepositoryByFile();
		ur = new UsersRepositoryByFile();
		
		cafe =new Cafe(or, pr, ur);
		
		SpringApplication.run(BackendApplication.class, args);
	}
	
	@GetMapping("/products")
	public List<Product> getProducts() throws Exception 
	{
		return cafe.getAvailableProducts(); 
	}
	
	@GetMapping("/orders")
	public List<Order> getAllOrders() throws Exception 
	{
		return cafe.getRegisteredOrders(); 
	}
	
	
	
	@GetMapping("/productTypes")
	public List<ProductType> getProductTypes() throws Exception 
	{
		return cafe.getAvailableProductTypes(); 
	}
	
	//Esto saca dos veces el parámetro del tipo de producto
	@JsonIgnore
	@GetMapping("/products/type")
	public List<Product> getProductsByType(@RequestParam int id) throws Exception 
	{
		return cafe.getAvailableProductsbyType(id); 
	}

	@GetMapping("/users")
	public List<User> getUsers() throws Exception 
	{
		return cafe.getRegisteredUsers(); 
	}
	
	
	@GetMapping("/users/orders/{iduser}")
	public List<Order> getUserOrders(@PathVariable int iduser ) throws Exception 
	{
		Optional<User> u = cafe.getUserById(iduser);
		List<Order> orders = null;
		if(u.isPresent()) 
			orders = cafe.getUserOrders(u.get());
		
		return orders;  
	}
	
	//RequestParam va en la URI
	//RequestBody va en la petición HTTP
	@PostMapping("/users")
	public long newUser(@RequestBody User newUser) throws Exception 
	{
		return cafe.registerUser(newUser);
	}
	
	
	@PostMapping("/order/time")
	public void setPickUpTime( @RequestParam int idorder, @RequestParam LocalDateTime ldt) throws Exception 
	{
		if(cafe.getOrderById(idorder).isPresent())
			cafe.getOrderById(idorder).get().setPickUpTime(ldt);
	}
	
	@PostMapping("/order/user") //primero create user, y le pasamos el id user aqui
	public Order createOrder(@RequestParam int idUser) 
	{
		return cafe.newOrder(idUser);
	}
	
	@PostMapping("/order/product")
	public void addProduct(@RequestParam(required=true) int idproduct, @RequestParam int cant, @RequestParam(required=true) int idorder ) 
	{
		if(cafe.getProductById(idproduct).isPresent() && cafe.getOrderById(idorder).isPresent() ) {
			Product newProduct = cafe.getProductById(idproduct).get();
			cafe.getOrderById(idorder).get().addProductToOrder(newProduct, cant);
		}
	}
	
	@PostMapping("/order/delete")
	public void deleteProduct(@RequestParam(required=true) int idproduct, @RequestParam(required=true) int cant,
			@RequestParam(required=true, name="idorder") int idorder ) 
	{
		if(cafe.getProductById(idproduct).isPresent() && cafe.getOrderById(idorder).isPresent() ) 
		{	
			List<OrderLine> orderLineProducts = cafe.getOrderById(idorder).get().getProducts();
			Product oldProduct = cafe.getProductById(idproduct).get();
			
			for(OrderLine ol: orderLineProducts )
				if(ol.getProduct()==oldProduct) {
					cafe.getOrderById(idorder).get().deleteProductFromOrder( cafe.getProductById(idproduct).get(), cant);
					return;
				}
		}
	}
	
	@GetMapping("/cashbox")
	public CashBox getCashBox(@RequestParam LocalDate ld) {
		
		if(ld==null)
			return cafe.getTodayCashBox();
		else
			return cafe.getCashBox(ld);
	}

	@PostMapping("/order/end")
	public void finishOrder(@RequestParam(required=true) int idorder) throws Exception 
	{
		EnvioEmail mail = new EnvioEmail();
		if(  cafe.getOrderById(idorder).isPresent() ) 
		{
			Order order =  cafe.getOrderById(idorder).get();
			mail.sendEmail(order);
			cafe.FinishOrder(order);
		}
	}
	
	@PutMapping("/user/firstname")
	public void updateUserFirstName(@RequestParam(required=true) int id_user, @RequestParam(required=true) String firstname) throws Exception 
	{
		User user = cafe.getUserById(id_user).get();
		user.setFirstName(firstname);
	}
	
	@PutMapping("/user/lastname")
	public void updateUserLastName(@RequestParam(required=true) int id_user, @RequestParam(required=true) String lastname) throws Exception 
	{
		User user = cafe.getUserById(id_user).get();
		user.setFirstName(lastname);
	}
	
	@PutMapping("/user/dni")
	public void updateUserDni(@RequestParam(required=true) int id_user, @RequestParam(required=true) String dni) throws Exception 
	{
		User user = cafe.getUserById(id_user).get();
		user.setFirstName(dni);
	}
	
	@PutMapping("/user/birthdate")
	public void updateUserDni(@RequestParam(required=true) int id_user, @RequestParam(required=true) LocalDate birthdate) throws Exception 
	{
		User user = cafe.getUserById(id_user).get();
		user.setBirthDate(birthdate);
	}
	
	@PostMapping("/user/delete")
	public void deleteUser(@RequestParam(required=true) int id_user) throws Exception 
	{
		cafe.deleteUserbyId(id_user);
	}
	
	
	
}
