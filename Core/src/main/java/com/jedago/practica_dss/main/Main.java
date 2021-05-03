package com.jedago.practica_dss.main;

import java.util.List;

import com.jedago.practica_dss.cli.Screen;
import com.jedago.practica_dss.persistance.OrdersRepository;
import com.jedago.practica_dss.persistance.OrdersRepositoryByFile;
import com.jedago.practica_dss.persistance.ProductsRepository;
import com.jedago.practica_dss.persistance.ProductsRepositoryByFile;
import com.jedago.practica_dss.core.*;
public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		//Levar toda la estructura
		//Instanciar lo que haga falta
		//Inyectar las dependencias
		//Echarlo a andar
		OrdersRepository OR = new OrdersRepositoryByFile();
		ProductsRepository PR = new ProductsRepositoryByFile();
		
		ICafe cafe = new Cafe(OR, PR); 
		Screen screen = new Screen(cafe);
		
		screen.run();

	}

}
