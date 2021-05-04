package com.jedago.practica_dss.backend;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.jedago.practica_dss.core.exceptions.NoStockException;
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
	
	@JsonIgnore
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
	public List<Product> getProductsByType(@RequestParam String id) throws Exception 
	{
		return cafe.getAvailableProductsbyType(id); 
	}

	@JsonIgnore
	@GetMapping("/users")
	public List<User> getUsers() throws Exception 
	{
		return cafe.getRegisteredUsers(); 
	}
	
	
	@GetMapping("/users/orders/{iduser}")
	public List<Order> getUserOrders(@PathVariable String iduser ) throws Exception 
	{
		Optional<User> u = cafe.getUserById(iduser);
		List<Order> orders = null;
		if(u.isPresent()) 
		{
			orders = cafe.getUserOrders(u.get());
		}
		return orders;  
	}
	
	//RequestParam va en la URI
	//RequestBody va en la petición HTTP
	@PostMapping("/user")
	public String newUser(@RequestBody User newUser) throws Exception 
	{
		cafe.registerUser(newUser);
		return newUser.getIdUser();
	}
	
	
	@PostMapping("/order/time")
	public void setPickUpTime( @RequestParam String idorder, @RequestParam LocalDateTime ldt) throws Exception 
	{
		Order order = null;
		if(cafe.getOrderById(idorder).isPresent())
		{
			order = cafe.getOrderById(idorder).get();
			cafe.setPickUpTime(order, ldt);
		}
	}
	
	@PostMapping("/order/user") //primero create user, y le pasamos el id user aqui
	public String createOrder(@RequestParam String idUser) 
	{
		return cafe.newOrder(idUser).getId_order();
	}
	
	@PostMapping("/order/product")
	public void addProduct(@RequestParam(required=true) String idproduct, @RequestParam int cant, @RequestParam(required=true) String idorder ) throws NoStockException 
	{
		System.out.println("entra en addproduct");
		if(cafe.getProductById(idproduct).isPresent() &&  cafe.getOrderById(idorder).isPresent() ) {
			System.out.println("entra en el if de addproduct");

			Product newProduct = cafe.getProductById(idproduct).get();
			System.out.println(newProduct.getName());
			cafe.addProductToOrder(cafe.getOrderById(idorder).get(), newProduct, cant);
		}
	}
	
	@DeleteMapping("/order/delete")
	public void deleteProduct(@RequestParam(required=true) String idproduct, @RequestParam(required=true) int cant,
			@RequestParam(required=true, name="idorder") String idorder ) 
	{
		if(cafe.getProductById(idproduct).isPresent() && cafe.getOrderById(idorder).isPresent() ) 
		{	
			Product oldProduct = cafe.getProductById(idproduct).get();
			Order order = cafe.getOrderById(idorder).get();
			cafe.deleteProductFromOrder(order , oldProduct, cant );
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
	public void finishOrder(@RequestParam(required=true) String idorder) throws Exception 
	{
		EnvioEmail mail = new EnvioEmail();
		if(  cafe.getOrderById(idorder).isPresent() ) 
		{
			Order order =  cafe.getOrderById(idorder).get();
			mail.sendEmail(order);
			cafe.FinishOrder(order);
		}
	}
	
	@PatchMapping("/user/firstname")
	public void updateUserFirstName(@RequestParam(required=true) String id_user, @RequestParam(required=true) String firstname) throws Exception 
	{
		User user = cafe.getUserById(id_user).get();
		cafe.updateUserFirstName(user, firstname);

	}
	
	@PatchMapping("/user/lastname")
	public void updateUserLastName(@RequestParam(required=true) String id_user, @RequestParam(required=true) String lastname) throws Exception 
	{
		User user = cafe.getUserById(id_user).get();
		cafe.updateUserLastName(user, lastname);
	}
	
	@PatchMapping("/user/dni")
	public void updateUserDni(@RequestParam(required=true) String id_user, @RequestParam(required=true) String dni) throws Exception 
	{
		User user = cafe.getUserById(id_user).get();
		cafe.updateUserDNI(user, dni);

	}
	
	@PatchMapping("/user/birthdate")
	public void updateUserBirthdate(@RequestParam(required=true) String id_user, @RequestParam(required=true) String birthdate) throws Exception 
	{	
		LocalDate localDate1 = LocalDate.parse(birthdate);
		User user = cafe.getUserById(id_user).get();
		cafe.updateUserBirthDate(user, localDate1);
	}
	
	@DeleteMapping("/user/delete")
	public void deleteUser(@RequestParam(required=true) String id_user) throws Exception 
	{
		cafe.deleteUserbyId(id_user);
	}
	
}
