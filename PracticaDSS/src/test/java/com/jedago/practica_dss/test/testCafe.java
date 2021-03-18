package com.jedago.practica_dss.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jedago.practica_dss.core.*;

public class testCafe {
	private List<Order> lista_pedidos;
	private List<Product> lista_productos;
	private Product p1;
	private Cafe C;

	@Before
	public void setUp() throws Exception {
		//Crear una lista pedidos y una lista productos
		lista_pedidos = new ArrayList<Order>();
		lista_productos = new ArrayList<Product>();
		p1 = new Product("Producto1", 2, 2.5);
		lista_productos.add(p1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void constructor() {
		//Crear el cafe
		
		//Comprobar que el cafe no sea nulo
	}

}
