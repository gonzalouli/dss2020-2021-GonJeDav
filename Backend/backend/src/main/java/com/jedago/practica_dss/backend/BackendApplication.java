package com.jedago.practica_dss.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jedago.practica_dss.core.Cafe;
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
	
	//Esto no sabemos como va
	@PostMapping("/users/register")
	public void newUser(@RequestBody User newUser) throws Exception 
	{
		cafe.registerUser(newUser);
	}
	
	
	
	
			

}
