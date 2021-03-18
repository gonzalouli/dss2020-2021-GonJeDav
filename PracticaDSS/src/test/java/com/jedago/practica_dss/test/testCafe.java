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
	private Order o;

	@Before //Antes de cada test
	public void setUp() throws Exception {
		//Crear una lista pedidos y una lista productos
		lista_pedidos = new ArrayList<Order>();
		lista_productos = new ArrayList<Product>();
		p1 = new Product("Producto1", 2, 2.5);
		lista_productos.add(p1);
		C = new Cafe(lista_pedidos, lista_productos);
	}

	@After
	public void tearDown() throws Exception {
		lista_pedidos = null;
		lista_productos = null;
		p1 = null;
		C = null;
	}

	@Test
	public void testConstructor() {
		//Crear el cafe
		
		assert(C != null);
	}
	
	@Test
	public void testNewOrder() {
		//Comprobar que devuelva una orden vacía
		o = C.newOrder();
		assert(o.isEmpty());
	}
	
	@Test
	public void testAddandDeleteProductToOrder() {
		//Comprobar que se añade una orderline a la orden actual con el producto
		C.addProductToOrder(o, p1);
		List<OrderLine> lp = o.getProducts();
		//Comprobar aumento del precio del pedido
		
		OrderLine ol = lp.get(0);
		assertEquals(ol.getProduct(), p1);
		assert(ol.getAmount() == 1);
		
		C.deleteProductFromOrder(o, p1);
		ol = lp.get(0);
		assert(ol == null);
	}
	
	@Test
	//
	public void testShowProducts() {
		//Comprobar que saque por pantalla los productos disponibles
		//??
	}
	
	@Test
	public void testShowCashBox() {
		//Comprobar que muestre por pantalla el número de pedidos registrados y el dinero total
		//??
	}
	
	//Para cada test, Before, Test, After
}
