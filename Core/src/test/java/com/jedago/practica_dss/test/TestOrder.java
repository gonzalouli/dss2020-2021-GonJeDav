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


public class TestOrder {
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
		ProductType t = new ProductType(1, "Bebidas");
		ProductType m = new ProductType(2, "Menu");

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
	public void TestConstructorSingle() //PASS
	{ 
		
		BigDecimal cero =  BigDecimal.ZERO ;
		LocalDateTime time = LocalDateTime.now();
		assert(order!=null);
		
		assertEquals( order.getDateTime().getYear(), time.getYear());
		assertEquals( order.getDateTime().getMonth(), time.getMonth());
		assertEquals( order.getDateTime().getDayOfMonth(), time.getDayOfMonth());
		assertEquals( order.getDateTime().getHour(), time.getHour());
		assertEquals( order.getDateTime().getMinute(), time.getMinute());

		assertEquals( order.getId_order(), Order.currentid-1);
		
		Order secondOrder = new Order();		
		assertEquals( order.getId_order(), secondOrder.getId_order()-1);

		
		assertEquals(cero ,order.getPrice());
		
}


	@Test
	public void TestgetId_oderSingle() //PASS
	{ 
		Order orden = new Order();
		assertEquals( Order.currentid -1, orden.getId_order());
		
	}
//	

	@Test
	public void TestGetPriceSingle() //PASS
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
	public void TestSetProductSingle() //PASS
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
	public void TestSetNProductSingle() //PASS
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
	public void TestAddProductToOrderSingle() //PASS
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
	public void TestAddNProductToOrderSingle() //PASS
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
	public void TestDeleteProductFromOrderSingle() //PASS
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
	public void TestDeleteOrderlineFromOrderSingle() //PASS
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
public void TestConstructorMenu() //PASS
{ 
	
	BigDecimal cero =  BigDecimal.ZERO ;
	LocalDateTime time = LocalDateTime.now();
	assert(order!=null);
	
	assertEquals( order.getDateTime().getYear(), time.getYear());
	assertEquals( order.getDateTime().getMonth(), time.getMonth());
	assertEquals( order.getDateTime().getDayOfMonth(), time.getDayOfMonth());
	assertEquals( order.getDateTime().getHour(), time.getHour());
	assertEquals( order.getDateTime().getMinute(), time.getMinute());

	
	assertEquals( order.getId_order(), Order.currentid-1);
	assertEquals(cero ,order.getPrice());
}

	
	@Test
	public void TestGetId_oderMenu() //PASS
	{ 
		Order orden = new Order();
		assertEquals( Order.currentid -1, orden.getId_order());
		
	}
	//
	
	@Test
	public void TestGetPriceMenu() //PASS
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
	public void TestSetProductMenu() //PASS
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
	public void TestSetNProductMenu() //PASS
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
	public void TestAddProductToOrderMenu() //PASS
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
	public void TestAddNProductToOrderMenu() //PASS
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
	public void TestDeleteProductFromOrderMenu() //PASS
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
	public void TestDeleteOrderlineFromOrderMenu() //PASS
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
	public void TestConstructorMinutes() throws DataFormatException 
	{
		Order order = new Order();
		LocalDateTime ldtnow = LocalDateTime.now();
		LocalDateTime ldt = LocalDateTime.of(ldtnow.getYear(), ldtnow.getMonthValue(), ldtnow.getDayOfMonth(), ldtnow.getHour(), ldtnow.plusMinutes(10).getMinute());
		 
		
		order.setPickUpTime(ldt);
		
		assertEquals(ldt,order.getPickUpTime() );
		assertNotEquals(ldt,ldtnow);
		
	}

	
	
}
	







	

