package com.jedago.practica_dss.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jedago.practica_dss.core.*;

public class testCafe {
	private List<Order> lista_pedidos;
	private List<Product> lista_productos;
	BigDecimal price1;
	private Product p1;
	private Cafe C;
	private Order o;
	private ProductType t;
	
	@Before //Antes de cada test
	public void setUp() throws Exception {
		//Crear una lista pedidos y una lista productos
		lista_pedidos = new ArrayList<Order>();
		lista_productos = new ArrayList<Product>();
		price1 = new BigDecimal(2.5);
		 t = new ProductType("Bocadillo");
		p1 = new Product("Producto1", 3, price1, t);
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
	public void testNewOrder() {
		//Comprobar que devuelva una orden vacía
		o = C.newOrder();
		assertTrue(o.isEmpty());
	}
	
	@Test
	public void testGetAvailableProducts() {
		//Comprobar que esté el producto p1, añadido desde el constructor de la lista de productos
		assertTrue(C.getAvailableProducts().contains(p1));
	}
	
	@Test
	public void testAddProductToOrder() {
		o = C.newOrder();
		BigDecimal oldPrice = o.getPrice();
		//Comprobar aumento del precio del pedido cuando se añade un producto
		C.addProductToOrder(o, p1);
		BigDecimal newPrice = o.getPrice();
		
		assertEquals(newPrice.subtract(oldPrice), p1.getPriceUnit());
	}
	
	@Test
	public void testAddSeveralProductToOrder() {
		o = C.newOrder();
		BigDecimal oldPrice = o.getPrice();
		//Comprobar aumento del precio del pedido cuando se añade un producto
		C.addProductToOrder(o, p1, 2);
		BigDecimal newPrice = o.getPrice();
		
		assertEquals(newPrice.subtract(oldPrice), p1.getPriceUnit().multiply(new BigDecimal(2)));
	}
	
	@Test
	public void testDeleteProductFromOrder() {
		o = C.newOrder();
		C.addProductToOrder(o, p1);
		BigDecimal oldPrice = o.getPrice();
		//Comprobar descenso del precio del pedido cuando se elimina un producto
		C.deleteProductFromOrder(o, p1);
		BigDecimal newPrice = o.getPrice();
		
		assertEquals(oldPrice.subtract(newPrice), p1.getPriceUnit());
	}
	
	@Test
	public void testDeleteSeveralProductsFromOrder() {
		o = C.newOrder();
		C.addProductToOrder(o, p1, 3);
		BigDecimal oldPrice = o.getPrice();
		//Comprobar descenso del precio del pedido cuando se eliminan producto
		C.deleteProductFromOrder(o, p1, 3);
		BigDecimal newPrice = o.getPrice();
		
		assertEquals(oldPrice.subtract(newPrice), p1.getPriceUnit().multiply(new BigDecimal(3)));
	}
	
	@Test
	public void testDeleteNotAllSameProductsFromOrder() {
		o = C.newOrder();
		C.addProductToOrder(o, p1, 3);
		BigDecimal oldPrice = o.getPrice();
		//Comprobar descenso del precio del pedido cuando se elimina un producto
		C.deleteProductFromOrder(o, p1, 2);
		BigDecimal newPrice = o.getPrice();
		
		assertEquals(oldPrice.subtract(newPrice), p1.getPriceUnit().multiply(new BigDecimal(2)));
	}
	
	@Test
	public void testFinishOrderRemovingProduct() {
		o = C.newOrder();
		C.addProductToOrder(o, p1, 3);
		C.FinishOrder(o);
		
		//Debería de eliminarse el p1 de la lista de productos disponibles
		assertTrue(!C.getAvailableProducts().contains(p1));
	}
	
	@Test
	public void testFinishOrderNotRemovingProduct() {
		o = C.newOrder();
		C.addProductToOrder(o, p1, 2);
		C.FinishOrder(o);
		
		//Debería estar p1 en la lista de productos disponibles
		assertTrue(C.getAvailableProducts().contains(p1));
		
		//Con stock = 1
		int i = C.getAvailableProducts().indexOf(p1);
		assertTrue(C.getAvailableProducts().get(i).getStock() == 1);
	}
	
	@Test
	public void testGetTodayCashBox() {
		o = C.newOrder();
		C.addProductToOrder(o, p1, 3);
		C.FinishOrder(o);
		assertEquals(C.getTodayCashBox().getnOrders(),  1);
		assertEquals(C.getTodayCashBox().getTotal(), p1.getPriceUnit().multiply(new BigDecimal(3)));
	}
	
	
}
