package com.jedago.practica_dss.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jedago.practica_dss.core.OrderLine;
import com.jedago.practica_dss.core.Product;

public class testOrderLine {
	
	private int amount;
	private OrderLine orderline;
	private Product p;
	private int cash = 2;
	private BigDecimal precio = BigDecimal.valueOf(cash);

	@Before
	public void setup() 
	{
		
		amount = 3;
		p = new Product("producto", 4, precio);
		orderline = new OrderLine(p, amount);

	}
	
	@After
	public void teardown()
	{
		
		p = null;
		amount = 0;
		
	}
	
	@Test
	public void testConstructor() 
	{
		assertNotNull(p);
		assertEquals(amount, 3);
		assertEquals(orderline.getProduct(), p);
		
	}
	
	@Test
	public void testgetProduct() 
	{
		
		assertEquals(orderline.getProduct(),p);
		
	}
	
	@Test
	public void testsetProduct() 
	{
		
		Product newproduct = new Product("testproduct",1,precio);
		
		orderline.setProduct(newproduct);
		
		assertEquals(orderline.getProduct(), newproduct);
		
		
	}
	
	@Test
	public void testgetProductName() 
	{
		
		assertEquals(p.getName(), "producto");
		
	}
	
	@Test
	public void testgetAmount() 
	{
		
		assertEquals(orderline.getAmount(),amount);
		
	}
	
	@Test
	public void testsetAmount() 
	{
		
		int testamount = 2;
		orderline.setAmount(testamount);

		assertEquals(orderline.getAmount(),2);
		
	}
	
	@Test
	public void testgetTotalPrice() 
	{
		
		BigDecimal testamount = BigDecimal.valueOf(amount);
		assertEquals(orderline.getTotalPrice(), 
				orderline.getProduct().getPriceUnit().multiply(testamount));
		
	}
	
}
