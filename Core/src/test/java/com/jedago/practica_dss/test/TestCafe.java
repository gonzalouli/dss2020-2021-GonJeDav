package com.jedago.practica_dss.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jedago.practica_dss.core.Cafe;
import com.jedago.practica_dss.core.Order;
import com.jedago.practica_dss.core.Product;
import com.jedago.practica_dss.core.ProductType;
import com.jedago.practica_dss.core.SingleProduct;
import com.jedago.practica_dss.core.exceptions.NoStockException;
import com.jedago.practica_dss.persistance.OrdersRepository;
import com.jedago.practica_dss.persistance.OrdersRepositoryOnMemory;
import com.jedago.practica_dss.persistance.ProductsRepository;
import com.jedago.practica_dss.persistance.ProductsRepositoryOnMemory;


public class TestCafe {
	private List<Product> lista_productos;
	private List<Order> lista_pedidos;
	BigDecimal price1;
	private Product p1;
	private Cafe C;
	private Order o;
	private ProductType t;
	private ProductsRepository PR;
	private OrdersRepository OR;
	
	@Before //Antes de cada test
	public void setUp() throws Exception {
		lista_productos = new ArrayList<Product>();
		lista_pedidos = new ArrayList<Order>();
		price1 = new BigDecimal(2.5);
		 t = new ProductType("Bocadillo");
		p1 = new SingleProduct(1, "Producto1", 3, price1, t);
		lista_productos.add(p1);
		PR = new ProductsRepositoryOnMemory();
		PR.writeProducts(lista_productos);
		OR = new OrdersRepositoryOnMemory();
		OR.writeOrders(lista_pedidos);
		C = new Cafe(OR, PR);
	}

	@After
	public void tearDown() throws Exception {
		lista_productos = null;
		p1 = null;
		PR = null;
		C = null;
	}
	
	@Test
	public void TestNewOrder() {
		//Comprobar que devuelva una orden vacía
		o = C.newOrder();
		assertTrue(o.isEmpty());
	}
	
	@Test
	public void TestGetAvailableProducts() {
		//Comprobar que esté el producto p1, añadido desde el constructor de la lista de productos
		assertTrue(C.getAvailableProducts().contains(p1));
	}
	
	@Test
	public void TestAddProductToOrder() {
		o = C.newOrder();
		BigDecimal oldPrice = o.getPrice();
		//Comprobar aumento del precio del pedido cuando se añade un producto
		try {
			C.addProductToOrder(o, p1);
		} catch (NoStockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal newPrice = o.getPrice();
		
		assertEquals(newPrice.subtract(oldPrice), p1.getPrice());
	}
	
	@Test(expected=NoStockException.class)
	public void TestAddProductToOrderException() throws NoStockException {
		o = C.newOrder();
		//Comprobar lanzamiento de excepción
		C.addProductToOrder(o, p1, 4);
	}
	
	@Test
	public void TestAddSeveralProductToOrder() {
		o = C.newOrder();
		BigDecimal oldPrice = o.getPrice();
		//Comprobar aumento del precio del pedido cuando se añade un producto
		try {
			C.addProductToOrder(o, p1, 2);
		} catch (NoStockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal newPrice = o.getPrice();
		
		assertEquals(newPrice.subtract(oldPrice), p1.getPrice().multiply(new BigDecimal(2)));
	}
	
	@Test
	public void TestDeleteProductFromOrder() {
		o = C.newOrder();
		try {
			C.addProductToOrder(o, p1);
		} catch (NoStockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal oldPrice = o.getPrice();
		//Comprobar descenso del precio del pedido cuando se elimina un producto
		C.deleteProductFromOrder(o, p1);
		BigDecimal newPrice = o.getPrice();
		
		assertEquals(oldPrice.subtract(newPrice), p1.getPrice());
	}
	
	@Test
	public void TestDeleteSeveralProductsFromOrder() {
		o = C.newOrder();
		try {
			C.addProductToOrder(o, p1, 3);
		} catch (NoStockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal oldPrice = o.getPrice();
		//Comprobar descenso del precio del pedido cuando se eliminan producto
		C.deleteProductFromOrder(o, p1, 3);
		BigDecimal newPrice = o.getPrice();
		
		assertEquals(oldPrice.subtract(newPrice), p1.getPrice().multiply(new BigDecimal(3)));
	}
	
	@Test
	public void TestDeleteNotAllSameProductsFromOrder() {
		o = C.newOrder();
		try {
			C.addProductToOrder(o, p1, 3);
		} catch (NoStockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal oldPrice = o.getPrice();
		//Comprobar descenso del precio del pedido cuando se elimina un producto
		C.deleteProductFromOrder(o, p1, 2);
		BigDecimal newPrice = o.getPrice();
		
		assertEquals(oldPrice.subtract(newPrice), p1.getPrice().multiply(new BigDecimal(2)));
	}
	
	@Test
	public void TestFinishOrderRemovingProduct() throws Exception{
		o = C.newOrder();
		try {
			C.addProductToOrder(o, p1, 3);
			C.FinishOrder(o);
		} catch (NoStockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Debería de eliminarse el p1 de la lista de productos disponibles
		assertTrue(!C.getAvailableProducts().contains(p1));
	}
	
	@Test(expected=NoStockException.class)
	public void TestFinishOrderException() throws Exception{
		o = C.newOrder();
		C.addProductToOrder(o, p1, 2);
		//Como no está registrado el pedido, no se ha actualizado el stock
		C.addProductToOrder(o, p1, 2);
		//Debería saltar excepción cuando quiero terminar el pedido
		C.FinishOrder(o);
	}
	
	@Test
	public void TestFinishOrderNotRemovingProduct() throws Exception{
		o = C.newOrder();
		
		try {
			C.addProductToOrder(o, p1, 2);
			C.FinishOrder(o);
		} catch (NoStockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Debería estar p1 en la lista de productos disponibles
		assertTrue(C.getAvailableProducts().contains(p1));
		
		//Con stock = 1
		int i = C.getAvailableProducts().indexOf(p1);
		assertTrue(C.getAvailableProducts().get(i).getStock() == 1);
	}
	
	@Test
	public void TestGetTodayCashBox() throws Exception{
		o = C.newOrder();
		try {
			C.addProductToOrder(o, p1, 3);
			C.FinishOrder(o);
		} catch (NoStockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(C.getTodayCashBox().getnOrders(),  1);
		assertEquals(C.getTodayCashBox().getTotal(), p1.getPrice().multiply(new BigDecimal(3)));
	}
	
	
}
