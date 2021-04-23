package com.jedago.practica_dss.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import com.jedago.practica_dss.core.User;
import com.jedago.practica_dss.persistance.OrdersRepository;
import com.jedago.practica_dss.persistance.OrdersRepositoryOnMemory;
import com.jedago.practica_dss.persistance.ProductsRepository;
import com.jedago.practica_dss.persistance.ProductsRepositoryOnMemory;
import com.jedago.practica_dss.persistance.UsersRepository;
import com.jedago.practica_dss.persistance.UsersRepositoryOnMemory;


public class TestPersistanceOnMemory {
	private List<Product> lista_productos;
	private List<Order> lista_pedidos;
	private List<User> lista_users;
	private BigDecimal price1;
	private Product p1;
	private Order o;
	private Cafe withoutUsers, withUsers;
	private ProductType t;
	private User user1;
	private ProductsRepository PR;
	private OrdersRepository OR;
	private UsersRepository UR;
	
	@Before //Antes de cada test
	public void setUp() throws Exception {
		lista_productos = new ArrayList<Product>();
		lista_pedidos = new ArrayList<Order>();
		lista_users = new ArrayList<User>();
		price1 = new BigDecimal(2.5);
		 t = new ProductType(1, "Bocadillo");
		p1 = new SingleProduct(1, "Producto1", 3, price1, t);
		lista_productos.add(p1);
		PR = new ProductsRepositoryOnMemory();
		PR.save(lista_productos);
		OR = new OrdersRepositoryOnMemory();
		OR.save(lista_pedidos);
		user1 = new User("Usuario", "Uno", LocalDate.of(1988, 1, 2), "1234");
		lista_users.add(user1);
		UR = new UsersRepositoryOnMemory();
		UR.save(lista_users);
		withoutUsers = new Cafe(OR, PR);
		withUsers = new Cafe(OR, PR, UR);
	}

	@After
	public void tearDown() throws Exception {
		lista_productos = null;
		lista_pedidos = null;
		lista_users = null;
		price1 = null;
		t = null;
		p1 = null;
		user1 = null;
		PR = null;
		OR = null;
		UR = null;
		withoutUsers = null;
		withUsers = null;
	}
	
	@Test
	public void TestConstructor() {
		//Comprobar que los cafés se construyan adecuadamente
		List<Product> products;
		List<Order> orders;
		List<User> users;
		
		products = withoutUsers.getAvailableProducts();
		orders = withoutUsers.getRegisteredOrders();
		assertEquals(products, lista_productos);
		assertEquals(orders, lista_pedidos);
		
		products = withUsers.getAvailableProducts();
		orders = withUsers.getRegisteredOrders();
		users = withUsers.getRegisteredUsers();
		assertEquals(products, lista_productos);
		assertEquals(orders, lista_pedidos);
		assertEquals(users, lista_users);
	}
	
	@Test
	public void TestFinishOrder() throws Exception
	{
		List<Product> products;
		//List<Order> orders;
		//List<User> users;
		
		o = withoutUsers.newOrder();
		withoutUsers.addProductToOrder(o, p1, 2);
		withoutUsers.FinishOrder(o);
		
		products = withoutUsers.getAvailableProducts();
		assertTrue(products.contains(p1));
		assertTrue(p1.getStock() == 1);
		
		o = withUsers.newOrder();
		withUsers.addProductToOrder(o, p1, 1);
		withUsers.FinishOrder(o);
		
		products = withUsers.getAvailableProducts();
		assertTrue(!products.contains(p1));
		assertTrue(p1.getStock() == 0);
	}
	
	@Test
	public void TestFindProductByType() throws Exception
	{
		assertTrue(PR.findAllStockAvailableByType(t).contains(p1));
	}
}
