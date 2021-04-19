package com.jedago.practica_dss.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jedago.practica_dss.core.Menu;
import com.jedago.practica_dss.core.Order;
import com.jedago.practica_dss.core.OrderLine;
import com.jedago.practica_dss.core.Product;
import com.jedago.practica_dss.core.ProductType;
import com.jedago.practica_dss.core.SingleProduct;


public class testOrder {
	double error = 0.001;
	private int cant=1;
	double cash = 2.5;
	private Product p;
	private Menu men;
	private OrderLine testOrderLineProductSimple;
	private OrderLine testOrderLineProductMenu;

	private Order order;
	private static int idProduct=0;
	private List<OrderLine> ol;
	
	@Before
	public void setup() 
	{
		
		BigDecimal precio = BigDecimal.valueOf(cash);
		ProductType t = new ProductType("Bebidas");
		ProductType m = new ProductType("Menu");

		p = new SingleProduct(++idProduct, "producto1", 4, precio, t);
		
		Product p2 = new SingleProduct(++idProduct, "producto2", 5, precio, t);
		men = new Menu(++idProduct,"Menu1", m);
		
		men.add(p);
		men.add(p2);
		
		testOrderLineProductSimple = new OrderLine(p,cant);
		testOrderLineProductMenu = new OrderLine(men,cant);

		
		ol = new ArrayList<OrderLine>();
		order = new Order();		
		
		
		ol.add(testOrderLineProductSimple);
		ol.add(testOrderLineProductMenu);
	}
	
	@After
	public void teardown() 
	{
		order = null;
		testOrderLineProductSimple = null ;
		testOrderLineProductMenu = null;
		p = null;
		men=null;
	}
	
	
	@Test
	public void testConstructorSingle() //PASS
	{ 
		
		BigDecimal cero =  BigDecimal.ZERO ;
		LocalDateTime time = LocalDateTime.now();
		assert(order!=null);
		
		assertEquals( order.getDate().getYear(), time.getYear());
		assertEquals( order.getDate().getMonth(), time.getMonth());
		assertEquals( order.getDate().getDayOfMonth(), time.getDayOfMonth());
		assertEquals( order.getDate().getHour(), time.getHour());
		assertEquals( order.getDate().getMinute(), time.getMinute());

		assertEquals( order.getId_order(), Order.currentid-1);
		assertEquals(cero ,order.getPrice());
		
}


	@Test
	public void testgetId_oderSingle() //PASS
	{ 
		Order orden = new Order();
		assertEquals( Order.currentid -1, orden.getId_order());
		
	}
//	

	@Test
	public void testGetPriceSingle() //PASS
	{

		order.setProducts(testOrderLineProductSimple);

		BigDecimal priceinside = BigDecimal.ZERO;
		
		for(int i=0 ; i<order.size() ; i++)
			ol.add(i,order.getProducts().get(i));
		
		
		for(int i=0 ; i<order.size() ; i++)
			priceinside = priceinside.add(ol.get(i).getTotalPrice());
		
		assert(priceinside.equals(order.getPrice()));
		
	}
	
	
	@Test
	public void testSetProductSingle() //PASS
	{
		
		testOrderLineProductSimple = new OrderLine(p,1);
		BigDecimal preprice = order.getPrice();
		
		order.setProducts( testOrderLineProductSimple );
		
		List<OrderLine> ol = order.getProducts();
		
		assert(ol.contains(testOrderLineProductSimple));
		
		preprice = preprice.add(testOrderLineProductSimple.getTotalPrice());
		assertEquals(order.getPrice(), preprice);
		
	}
	
	@Test
	public void testSetNProductSingle() //PASS
	{
		
		BigDecimal preprice = order.getPrice();
		
		order.setProducts( testOrderLineProductSimple );
		
		List<OrderLine> ol = order.getProducts();
		Boolean notin=true;
		
		for(int i=0 ; i<ol.size() && notin==true ; i++) {
			if(ol.get(i).getProduct().getID() == p.getID()) {
				notin =false;
			}
		}
		
		assert(!notin);
		
		BigDecimal amount = BigDecimal.valueOf(testOrderLineProductSimple.getAmount());
		
		preprice = preprice.add(testOrderLineProductSimple.getTotalPrice().multiply(amount));
		
		assertEquals(order.getPrice(), preprice);
		
	}
	
	
	@Test 
	public void testAddProductToOrderSingle() //PASS
	{
		List<OrderLine> preol = order.getProducts();
		Boolean exit = true;
		int precant =0;
		
		for(int i = 0 ; i<preol.size() && exit ; i++)
			if(preol.get(i).getProduct().getID() == p.getID()) {
				precant = preol.get(i).getAmount();
				exit = false;
			}
				
		order.addProductToOrder(p); 
		
		List<OrderLine> ol = order.getProducts();
		exit = true;
		
		for(int i = 0 ; i<preol.size() && exit ; i++)
			if(ol.get(i).getProduct().getID() == p.getID()) {
				assertEquals(precant+1, ol.get(i).getAmount());
				assertEquals(ol.get(i).getTotalPrice(), ol.get(i).getProduct().getPrice());
				exit = false;
			}
	}
		
	@Test	
	public void testAddNProductToOrderSingle() //PASS
	{
		List<OrderLine> preol = order.getProducts();
		Boolean exit = true;
		int precant =0;
		
		for(int i = 0 ; i<preol.size() && exit ; i++)
			if(preol.get(i).getProduct().getID() == p.getID()) {
				precant = preol.get(i).getAmount();
				exit = false;
			}
			
		order.addProductToOrder(p); 
		
		List<OrderLine> ol = order.getProducts();
		exit = true;
		
		for(int i = 0 ; i<preol.size() && exit ; i++)
			if(ol.get(i).getProduct().getID() == p.getID()) {
				assertEquals(precant+1, ol.get(i).getAmount());
				BigDecimal postamount = BigDecimal.ZERO;
				postamount = new BigDecimal(ol.get(i).getAmount());
				assertEquals(ol.get(i).getTotalPrice(), ol.get(i).getProduct().getPrice().multiply(postamount));
				exit = false;
			}
	}
		
	
	@Test
	public void testDeleteProductFromOrderSingle() //PASS
	{
		order.deleteProductFromOrder(p,1);
		List<OrderLine> delprod = order.getProducts();

		for(int i= 0; i<delprod.size() ; i++) 
			if(p == delprod.get(i).getProduct()) 
			{
				assertEquals(delprod.get(i).getAmount(), 3);
				return;
			}
		
	}
	
	
	
	@Test
	public void testDeleteOrderlineFromOrderSingle() //PASS
	{
		Boolean exit = true;
		
		List<OrderLine> preol = order.getProducts();
		
		for(int i = 0 ; i<preol.size() && exit ; i++) {
			if(	preol.get(i).getProduct().getID() == testOrderLineProductSimple.getProduct().getID() ) {
				exit = false;
			}
			assert(exit);
		}
	}

	
//####################################MenuTests##########################################


@Test
public void testConstructorMenu() //PASS
{ 
	
	BigDecimal cero =  BigDecimal.ZERO ;
	LocalDateTime time = LocalDateTime.now();
	assert(order!=null);
	
	assertEquals( order.getDate().getYear(), time.getYear());
	assertEquals( order.getDate().getMonth(), time.getMonth());
	assertEquals( order.getDate().getDayOfMonth(), time.getDayOfMonth());
	assertEquals( order.getDate().getHour(), time.getHour());
	assertEquals( order.getDate().getMinute(), time.getMinute());

	
	assertEquals( order.getId_order(), Order.currentid-1);
	assertEquals(cero ,order.getPrice());
}

	
	@Test
	public void testGetId_oderMenu() //PASS
	{ 
		Order orden = new Order();
		assertEquals( Order.currentid -1, orden.getId_order());
		
	}
	//
	
	@Test
	public void testGetPriceMenu() //PASS
	{
	
		order.setProducts(testOrderLineProductMenu);
	
		BigDecimal priceinside = BigDecimal.ZERO;
		
		for(int i=0 ; i<order.size() ; i++)
			ol.add(i,order.getProducts().get(i));
		
		
		for(int i=0 ; i<order.size() ; i++)
			priceinside = priceinside.add(ol.get(i).getTotalPrice());
		
		assert(priceinside.equals(order.getPrice()));
		
	}
	
	
	@Test
	public void testSetProductMenu() //PASS
	{
		
		testOrderLineProductMenu = new OrderLine(men,1);
		BigDecimal preprice = order.getPrice();
		
		order.setProducts( testOrderLineProductMenu );
		
		List<OrderLine> ol = order.getProducts();
		
		assert(ol.contains(testOrderLineProductMenu));
		
		preprice = preprice.add(testOrderLineProductMenu.getTotalPrice());
		assertEquals(order.getPrice(), preprice);
		
	}
	
	@Test
	public void testSetNProductMenu() //PASS
	{
		
		BigDecimal preprice = order.getPrice();
		
		order.setProducts( testOrderLineProductMenu );
		
		List<OrderLine> ol = order.getProducts();
		Boolean notin=true;
		
		for(int i=0 ; i<ol.size() && notin==true ; i++) {
			if(ol.get(i).getProduct().getID() == men.getID()) {
				notin =false;
			}
		}
		
		assert(!notin);
		
		BigDecimal amount = BigDecimal.valueOf(testOrderLineProductMenu.getAmount());
		
		preprice = preprice.add(testOrderLineProductMenu.getTotalPrice().multiply(amount));
		
		assertEquals(order.getPrice(), preprice);
		
	}
	
	
	@Test 
	public void testAddProductToOrderMenu() //PASS
	{
		List<OrderLine> preol = order.getProducts();
		Boolean exit = true;
		int precant =0;
		
		for(int i = 0 ; i<preol.size() && exit ; i++)
			if(preol.get(i).getProduct().getID() == men.getID()) {
				precant = preol.get(i).getAmount();
				exit = false;
			}
				
		order.addProductToOrder(men); 
		
		List<OrderLine> ol = order.getProducts();
		exit = true;
		
		for(int i = 0 ; i<preol.size() && exit ; i++)
			if(ol.get(i).getProduct().getID() == men.getID()) {
				assertEquals(precant+1, ol.get(i).getAmount());
				assertEquals(ol.get(i).getTotalPrice(), ol.get(i).getProduct().getPrice());
				exit = false;
			}
	}
		
	@Test	
	public void testAddNProductToOrderMenu() //PASS
	{
		List<OrderLine> preol = order.getProducts();
		Boolean exit = true;
		int precant =0;
		
		for(int i = 0 ; i<preol.size() && exit ; i++)
			if(preol.get(i).getProduct().getID() == men.getID()) {
				precant = preol.get(i).getAmount();
				exit = false;
			}
			
		order.addProductToOrder(men); 
		
		List<OrderLine> ol = order.getProducts();
		exit = true;
		
		for(int i = 0 ; i<preol.size() && exit ; i++)
			if(ol.get(i).getProduct().getID() == men.getID()) {
				assertEquals(precant+1, ol.get(i).getAmount());
				BigDecimal postamount = BigDecimal.ZERO;
				postamount = new BigDecimal(ol.get(i).getAmount());
				assertEquals(ol.get(i).getTotalPrice(), ol.get(i).getProduct().getPrice().multiply(postamount));
				exit = false;
			}
	}
		
	
	@Test
	public void testDeleteProductFromOrderMenu() //PASS
	{
		order.deleteProductFromOrder(men,1);
		List<OrderLine> delprod = order.getProducts();
	
		for(int i= 0; i<delprod.size() ; i++) 
			if(men == delprod.get(i).getProduct()) 
			{
				assertEquals(delprod.get(i).getAmount(), 3);
				return;
			}
		
	}
	
	
	
	@Test
	public void testDeleteOrderlineFromOrderMenu() //PASS
	{
		Boolean exit = true;
		
		List<OrderLine> preol = order.getProducts();
		
		for(int i = 0 ; i<preol.size() && exit ; i++) {
			if(	preol.get(i).getProduct().getID() == testOrderLineProductSimple.getProduct().getID() ) {
				exit = false;
			}
			assert(exit);
		}
	}
	
	
	@Test
	public void testConstructorMinutes() throws DataFormatException 
	{
		Order orderMinutes = new Order();
	
		orderMinutes.setCollectedDate(7);

		System.out.println(orderMinutes.getDate().getMinute());
		System.out.println(orderMinutes.getDate().getHour());

		
		assertEquals(orderMinutes.getCollectedDate().getMinute(), 7);
	}
	
	@Test
	public void testConstructorHoursMinutes() throws DataFormatException 
	{
		Order orderMinutes = new Order();
		orderMinutes.setCollectedDate(15, 30);
		LocalDateTime now =  LocalDateTime.now();
		
		assertEquals(orderMinutes.getCollectedDate().getMonth(), now.getMonth());
		assertEquals(orderMinutes.getCollectedDate().getYear(), now.getYear());

		assertEquals(orderMinutes.getCollectedDate().getHour(), 15);
		assertEquals(orderMinutes.getCollectedDate().getMinute(), 30);
	}
	
	@Test
	public void testConstructorDayHoursMinutes() throws DataFormatException 
	{
		Order orderMinutes = new Order();
		LocalDateTime now =  LocalDateTime.now();
		
		orderMinutes.setCollectedDate(2, 15, 30);

		
		assertEquals(orderMinutes.getCollectedDate().getMonth(), now.getMonth());
		assertEquals(orderMinutes.getCollectedDate().getYear(), now.getYear());

		assertEquals(orderMinutes.getCollectedDate().getHour(), 15);
		assertEquals(orderMinutes.getCollectedDate().getMinute(), 30);
		assertEquals(orderMinutes.getCollectedDate().getDayOfMonth(), 2);
	}
	
	
}
	







	

