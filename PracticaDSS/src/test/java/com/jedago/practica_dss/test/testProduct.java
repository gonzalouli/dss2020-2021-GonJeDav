package com.jedago.practica_dss.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jedago.practica_dss.core.Product;

public class testProduct 
{
	
	private Product p;

	@Before
	public void setup() 
	{
		
		p = new Product("producto", 4, new BigDecimal("2"));
				
	}
	
	@After
	public void teardown()
	{
		p = null;
	}
	
	@Test
	public void testgetID() 
	{
		assertEquals(Product.currentId -1, p.getID());
	}
	
	@Test
	public void testgetName() 
	{
		assertEquals(p.getName(), "producto");
	}
	
	@Test
	public void testgetStock() 
	{
		assertEquals(p.getStock(), 4);
	}
	
	@Test
	public void testsetPriceUnit() 
	{
		BigDecimal newprice = new BigDecimal("1");
		p.setPriceUnit(newprice);
		System.out.println(p.getPriceUnit());
		assertEquals( new BigDecimal("1"), p.getPriceUnit());
	}
	
	@Test
	public void testsetStock() 
	{
		p.setStock(2);
		assertEquals(2, p.getStock());
	}
	
	@Test
	public void testequals()
	{
		Product sameproduct = p; 
		Product nameotherproduct = new Product("productodistinto", 4, new BigDecimal("2"));
		Product stockotherproduct = new Product("producto", 2, new BigDecimal("2"));
		Product priceotherproduct = new Product("producto", 4, new BigDecimal("4"));

		assert(p.equals(sameproduct));
		assert(!p.equals(nameotherproduct));
		assert(!p.equals(stockotherproduct));
		assert(!p.equals(priceotherproduct));	

	}

}
