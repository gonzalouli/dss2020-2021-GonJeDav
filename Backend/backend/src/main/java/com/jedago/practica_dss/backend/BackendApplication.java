package com.jedago.practica_dss.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jedago.practica_dss.core.Cafe;
import com.jedago.practica_dss.core.User;
import com.jedago.practica_dss.persistance.OrdersRepositoryByFile;
import com.jedago.practica_dss.persistance.ProductsRepositoryByFile;
import com.jedago.practica_dss.persistance.UsersRepositoryByFile;

@SpringBootApplication
@RestController
public class BackendApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	
	@PostMapping("/users")
	public void newUser(@RequestBody User newUser) throws Exception 
	{
		OrdersRepositoryByFile or = new OrdersRepositoryByFile();
		ProductsRepositoryByFile pr = new ProductsRepositoryByFile();
		UsersRepositoryByFile ur = new UsersRepositoryByFile();
		
		Cafe cafe =new Cafe(or, pr, ur);
		
		cafe.registerUser(newUser);
	}
	
	
	
	
	
			

}
