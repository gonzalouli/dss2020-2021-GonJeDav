package com.jedago.practica_dss.backend;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jedago.practica_dss.core.Client;

@SpringBootApplication
@RestController
public class BackendApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
	return String.format("Hello %s!", name);
	}
	
	
	
	@GetMapping("/createClient")
	public String createClient(@RequestParam(value = "firstname") String firstname, 
			@RequestParam(value = "lastname") String lastname,
			@RequestParam(value = "birthdate") String birthdate,
			@RequestParam(value = "dni") String dni) throws Exception {
		

		Client client = new Client(firstname, lastname, LocalDate.parse(birthdate)  , dni);
		
		return client.userToJson();
		
	}
	
	
	

}
