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

public class TestOrderLine {
	
	private int amount;
	private OrderLine testOrderLineProductSimple;
	private OrderLine testOrderLineProductMenu;
	private Product p2;
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

		
		p = new SingleProduct("producto", 4, precio, t);
	    p2 = new SingleProduct("producto2", 5, precio, t);
		amount = 1;
		men = new Menu("Menu1", m);
		
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
	public void TestConstructorSingle() 
	{
		assertNotNull(p);
		assertEquals(amount, 1);
		assertEquals(testOrderLineProductSimple.getProduct(), p);
		assertNotEquals(p.getID(),p2.getID());
		
	}
	
	@Test
	public void TestgetProductSingle() 
	{
		
		assertEquals(testOrderLineProductSimple.getProduct(),p);
		
	}
	
	@Test
	public void TestsetProductSingle() 
	{
		Product np = new SingleProduct("productoprueba", 2, precio, t);
		
		
		testOrderLineProductSimple.setProduct(np);
		
		assertEquals(testOrderLineProductSimple.getProduct(), np);
		
		
	}
	
	@Test
	public void TestgetProductNameSingle() 
	{
		
		assertEquals(p.getName(), "producto");
		
	}
	
	@Test
	public void TestgetAmountSingle() 
	{
		
		assertEquals(testOrderLineProductSimple.getAmount(),amount);
		
	}
	
	@Test
	public void TestsetAmountSingle() 
	{
		
		int testamount = 2;
		testOrderLineProductSimple.setAmount(testamount);

		assertEquals(testOrderLineProductSimple.getAmount(),2);
		
	}
	
	@Test
	public void TestgetTotalPriceSingle() 
	{
		
		BigDecimal testamount = BigDecimal.valueOf(amount);
		assertEquals(testOrderLineProductSimple.getTotalPrice(), 
				testOrderLineProductSimple.getProduct().getPrice().multiply(testamount));
		
	}
	


//###################################TestMenu####################################

@Test
public void TestConstructorMenu() 
{
	assertNotNull(men);
	assertEquals(testOrderLineProductMenu.getAmount(), 1);
	assertEquals(testOrderLineProductMenu.getProduct(), men);
	
}

@Test
public void TestgetProductMenu() 
{
	
	assertEquals(testOrderLineProductMenu.getProduct(),men);
	
}

@Test
public void TestsetProductMenu() 
{
	Menu m2 = new Menu("productomenuprueba", m);
	
	
	testOrderLineProductMenu.setProduct(m2);
	
	assertEquals(testOrderLineProductMenu.getProduct(), m2);
	assertNotEquals(m2.getID(),men.getID());
	

}

@Test
public void TestgetProductNameMenu() 
{			
			
	assertEquals(men.getName(), "Menu1: producto + producto2");
	
}

@Test
public void TestgetAmountMenu() 
{
	
	assertEquals(testOrderLineProductMenu.getAmount(),1);
	
}

@Test
public void TestsetAmountMenu() 
{
	
	int testamount = 2;
	testOrderLineProductMenu.setAmount(testamount);

	assertEquals(testOrderLineProductMenu.getAmount(),2);
	
}

@Test
public void TestgetTotalPriceMenu() 
{
	
	BigDecimal testamount = BigDecimal.valueOf(amount);
	assertEquals(testOrderLineProductMenu.getTotalPrice(), 
			testOrderLineProductMenu.getProduct().getPrice().multiply(testamount));
	
}

}
