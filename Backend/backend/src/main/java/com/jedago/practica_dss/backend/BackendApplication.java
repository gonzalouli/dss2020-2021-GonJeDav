package com.jedago.practica_dss.backend;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import com.jedago.practica_dss.backend.EnvioEmail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jedago.practica_dss.core.Cafe;
import com.jedago.practica_dss.core.CashBox;
import com.jedago.practica_dss.core.Order;
import com.jedago.practica_dss.core.Product;
import com.jedago.practica_dss.core.ProductType;
import com.jedago.practica_dss.core.User;
import com.jedago.practica_dss.persistance.OrdersRepositoryByFile;
import com.jedago.practica_dss.persistance.ProductsRepositoryByFile;
import com.jedago.practica_dss.persistance.UsersRepositoryByFile;
@SpringBootApplication
@RestController
public class BackendApplication {
	private static OrdersRepositoryByFile or;
	private static ProductsRepositoryByFile pr;
	private static UsersRepositoryByFile ur;
	private static Cafe cafe;
	
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
	@GetMapping("/products/type")
	public List<Product> getProductsByType(@RequestParam int id) throws Exception 
	{
		return cafe.getAvailableProductsbyTypebyId(id); 
	}

	@GetMapping("/users")
	public List<User> getUsers() throws Exception 
	{
		return cafe.getRegisteredUsers(); 
	}
	
	
	@GetMapping("/users/orders/{iduser}")
	public List<Order> getUserOrders(@PathVariable int iduser ) throws Exception 
	{
		User u = ur.findUserById(iduser);
		return cafe.getUserOrders(u);
		
	}
	
	
	
	
	
	//RequestParam va en la URI
	//RequestBody va en la petición HTTP
	@PostMapping("/users")
	public void newUser(@RequestBody User newUser) throws Exception 
	{
		cafe.registerUser(newUser);
	}
	
	
	@PostMapping("/order/time")
	public void setPickUpTime( @RequestBody int idorder, @RequestBody LocalDateTime ldt) throws Exception {
			
			or.findById(idorder).setPickUpTime(ldt);
	}
	
	@PostMapping("/order")
	public void createOrder(@RequestBody Order order) 
	{
		
	}
	
	@PostMapping("/order/product")
	public void addProduct(@RequestBody(required=true) int idproduct, @RequestBody int cant, @RequestParam(required=true) int idorder ) 
	{
		
	}
	
	@PostMapping("/order/delete")
	public void deleteProduct(@RequestBody(required=true) int idproduct, @RequestBody(required=true) int cant,
			@RequestParam(required=true, name="idorder") int idorder ) 
	{
		
	}
	
	
	@GetMapping("/cashbox")
	public CashBox getCashBox(@PathVariable LocalDate ld) {
		if(ld==null)
			return cafe.getCashBox(LocalDate.now());
		else
			return cafe.getCashBox(ld);
	}
	
	
	
	@PostMapping("/order/end")
	public void finishOrder(@RequestBody(required=true) int idorder) throws Exception 
	{
		EnvioEmail mail = new EnvioEmail();
		if( or.findById(idorder).isEmpty() ) 
		{
			Order order =  or.findById(idorder);
			mail.sendEmail(order);
		}
			
		
	}
	
	
	
	
			

}
