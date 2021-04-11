package com.jedago.practica_dss.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jedago.practica_dss.core.Product;
import com.jedago.practica_dss.core.ProductType;
import com.jedago.practica_dss.core.SingleProduct;

public class testProduct 
{
	
	private Product p;
	private ProductType t;
	@Before
	public void setup() 
	{
		ProductType t = new ProductType("cafe");
		p = new SingleProduct(1, "producto", 4, new BigDecimal("2"), t);
				
	}
	
	@After
	public void teardown()
	{
		p = null;
	}
	
	@Test
	public void testgetID() 
	{
		SingleProduct p2 = new SingleProduct(1, "producto2", 4, new BigDecimal("2"), t);
		assertEquals(p2.getID(), p.getID());
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
	public void testsetPrice() 
	{
		assertEquals( new BigDecimal(2), p.getPrice());
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
		ProductType h = new ProductType("bocadillo");
		Product nameotherproduct = new SingleProduct(3, "productodistinto", 4, new BigDecimal("2"), t);
		Product stockotherproduct = new SingleProduct(4, "producto", 2, new BigDecimal("2"), t);
		Product priceotherproduct = new SingleProduct(5, "producto", 4, new BigDecimal("4"), t);
		Product typeotherproduct = new SingleProduct(6, "producto", 4, new BigDecimal("4"), h);

		assert(p.equals(sameproduct));
		assert(!p.equals(nameotherproduct));
		assert(!p.equals(stockotherproduct));
		assert(!p.equals(priceotherproduct));	
		assert(!p.equals(typeotherproduct));	


	}

}
