package com.jedago.practica_dss.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jedago.practica_dss.core.Menu;
import com.jedago.practica_dss.core.Product;
import com.jedago.practica_dss.core.ProductType;
import com.jedago.practica_dss.core.SingleProduct;

public class testProduct 
{
	
	private Product cafe, sandwich;
	private ProductType bebida, bocadillo, menu;
	private int id;
	@Before
	public void setup() 
	{
		id = 0;
		bebida = new ProductType("Bebidas");
		bocadillo = new ProductType("Bocadillos");
		menu = new ProductType("Menus");
		
		cafe = new SingleProduct(++id, "Cafe con leche", 3, new BigDecimal("2"), bebida);	
		sandwich = new SingleProduct(++id, "Sandwich", 4, new BigDecimal("2"), bocadillo);
	}
	
	@After
	public void teardown()
	{
		bebida = null;
		bocadillo = null;
		menu = null;
		cafe = null;
		sandwich = null;
	}
	
	@Test
	public void testGetID() 
	{
		assertEquals(1, cafe.getID());
		assertEquals(2, sandwich.getID());
	}
	
	@Test
	public void testSingleProductGetName() 
	{
		assertEquals(cafe.getName(), "Cafe con leche");
		assertEquals(sandwich.getName(), "Sandwich");
	}
	
	@Test
	public void testMenuConstructors() 
	{
		List<Product> SPList = new ArrayList<Product>();
		Menu Des1 = new Menu(++id, "Desayuno", menu);
		assertTrue(Des1.getComponents().isEmpty());
		Des1.add(cafe);
		Des1.add(sandwich);
		
		SPList.add(cafe);
		SPList.add(sandwich);
		Menu Des2 = new Menu(++id, "Desayuno", menu, SPList);
		assertEquals(Des1.getComponents(), Des2.getComponents());
	}
	
	@Test
	public void testSingleProductGetStock() 
	{
		assertEquals(sandwich.getStock(), 4);
		assertEquals(cafe.getStock(), 3);
	}
	
	@Test
	public void testMenuGetStock() 
	{
		Menu Des1 = new Menu(++id, "Desayuno", menu);
		Des1.add(cafe);
		Des1.add(sandwich);
		assertEquals(Des1.getStock(), Math.min(cafe.getStock(), sandwich.getStock()));
	}
	
	@Test
	public void testSingleProductGetPrice() 
	{
		assertEquals( new BigDecimal(2), cafe.getPrice());
		assertEquals( new BigDecimal(2), sandwich.getPrice());
	}
	
	@Test
	public void testMenuGetPrice() 
	{
		Menu Des1 = new Menu(++id, "Desayuno", menu);
		Des1.add(cafe);
		Des1.add(sandwich);
		assertEquals( Des1.getPrice(), cafe.getPrice().add(sandwich.getPrice()));
	}
	
	@Test
	public void testSingleProductStock() 
	{
		cafe.setStock(2);
		assertEquals(2, cafe.getStock());
	}
	
	@Test
	public void testMenuProductStock() 
	{
		Menu Des1 = new Menu(++id, "Desayuno", menu);
		Des1.add(cafe);
		Des1.add(sandwich);
		assertEquals(Des1.getStock(), cafe.getStock());
		
		int oldCafeStock = cafe.getStock();
		int oldSandwichStock = sandwich.getStock();
		Des1.setStock(Des1.getStock()+1);
		assertEquals(cafe.getStock(), oldCafeStock +1);
		assertEquals(sandwich.getStock(), oldSandwichStock +1);
	}

}
