package com.jedago.practica_dss.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jedago.practica_dss.core.Menu;
import com.jedago.practica_dss.core.OrderLine;
import com.jedago.practica_dss.core.Product;
import com.jedago.practica_dss.core.ProductType;
import com.jedago.practica_dss.core.SingleProduct;

public class testOrderLine {
	
	private static int idProduct=0;
	private int amount;
	private OrderLine testOrderLineProductSimple;
	private OrderLine testOrderLineProductMenu;

	private Product p;
	private Menu men;
	private int cash = 2;
	private BigDecimal precio = BigDecimal.valueOf(cash);
	private ProductType t;
	private ProductType m;
	
	@Before
	public void setup() 
	{
		 t = new ProductType("Bebidas");
		 m = new ProductType("Menu");

		
		p = new SingleProduct(++idProduct,"producto", 4, precio, t);
		Product p2 = new SingleProduct(++idProduct, "producto2", 5, precio, t);
		amount = 1;
		men = new Menu(++idProduct,"Menu1", m);
		
		men.add(p);
		men.add(p2);
		
		testOrderLineProductSimple = new OrderLine(p,amount);
		testOrderLineProductMenu = new OrderLine(men,amount);


	}
	
	@After
	public void teardown()
	{
		
		p = null;
		amount = 0;
		
	}
	
	@Test
	public void testConstructorSingle() 
	{
		assertNotNull(p);
		assertEquals(amount, 1);
		assertEquals(testOrderLineProductSimple.getProduct(), p);
		
	}
	
	@Test
	public void testgetProductSingle() 
	{
		
		assertEquals(testOrderLineProductSimple.getProduct(),p);
		
	}
	
	@Test
	public void testsetProductSingle() 
	{
		Product np = new SingleProduct(++idProduct,"productoprueba", 2, precio, t);
		
		
		testOrderLineProductSimple.setProduct(np);
		
		assertEquals(testOrderLineProductSimple.getProduct(), np);
		
		
	}
	
	@Test
	public void testgetProductNameSingle() 
	{
		
		assertEquals(p.getName(), "producto");
		
	}
	
	@Test
	public void testgetAmountSingle() 
	{
		
		assertEquals(testOrderLineProductSimple.getAmount(),amount);
		
	}
	
	@Test
	public void testsetAmountSingle() 
	{
		
		int testamount = 2;
		testOrderLineProductSimple.setAmount(testamount);

		assertEquals(testOrderLineProductSimple.getAmount(),2);
		
	}
	
	@Test
	public void testgetTotalPriceSingle() 
	{
		
		BigDecimal testamount = BigDecimal.valueOf(amount);
		assertEquals(testOrderLineProductSimple.getTotalPrice(), 
				testOrderLineProductSimple.getProduct().getPrice().multiply(testamount));
		
	}
	


//###################################TestMenu####################################

@Test
public void testConstructorMenu() 
{
	assertNotNull(men);
	assertEquals(testOrderLineProductMenu.getAmount(), 1);
	assertEquals(testOrderLineProductMenu.getProduct(), men);
	
}

@Test
public void testgetProductMenu() 
{
	
	assertEquals(testOrderLineProductMenu.getProduct(),men);
	
}

@Test
public void testsetProductMenu() 
{
	Menu m2 = new Menu(++idProduct,"productomenuprueba", m);
	
	
	testOrderLineProductMenu.setProduct(m2);
	
	assertEquals(testOrderLineProductMenu.getProduct(), m2);
	
	

}

@Test
public void testgetProductNameMenu() 
{			
			
	assertEquals(men.getName(), "Menu1: producto + producto2");
	
}

@Test
public void testgetAmountMenu() 
{
	
	assertEquals(testOrderLineProductMenu.getAmount(),1);
	
}

@Test
public void testsetAmountMenu() 
{
	
	int testamount = 2;
	testOrderLineProductMenu.setAmount(testamount);

	assertEquals(testOrderLineProductMenu.getAmount(),2);
	
}

@Test
public void testgetTotalPriceMenu() 
{
	
	BigDecimal testamount = BigDecimal.valueOf(amount);
	assertEquals(testOrderLineProductMenu.getTotalPrice(), 
			testOrderLineProductMenu.getProduct().getPrice().multiply(testamount));
	
}

}
