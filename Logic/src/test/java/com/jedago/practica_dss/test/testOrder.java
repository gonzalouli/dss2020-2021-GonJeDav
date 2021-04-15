package com.jedago.practica_dss.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
		LocalDate time = LocalDate.now();
		assert(order!=null);
		assert(order!=null);
		
		assertEquals( order.getDate(), time);
		assertEquals( order.getId_order(), Order.currentid-1);
		assertEquals(cero ,order.getPrice());
		assertEquals(time, order.getDate());
}


	@Test
	public void testgetId_oderSingle() //PASS
	{ 
		Order orden = new Order();
		assertEquals( Order.currentid -1, orden.getId_order());
		
	}
//	

	@Test
	public void testgetPriceSingle() //PASS
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
	public void testsetProductSingle() //PASS
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
	public void testsetNProductSingle() //PASS
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
	public void testaddProductToOrderSingle() //PASS
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
	public void testaddNProductToOrderSingle() //PASS
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
	public void testdeleteProductFromOrderSingle() //PASS
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
	public void deleteOrderlineFromOrderSingle() //PASS
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
	LocalDate time = LocalDate.now();
	assert(order!=null);
	assert(order!=null);
	
	assertEquals( order.getDate(), time);
	assertEquals( order.getId_order(), Order.currentid-1);
	assertEquals(cero ,order.getPrice());
	assertEquals(time, order.getDate());
}


@Test
public void testgetId_oderMenu() //PASS
{ 
	Order orden = new Order();
	assertEquals( Order.currentid -1, orden.getId_order());
	
}
//

@Test
public void testgetPriceMenu() //PASS
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
public void testsetProductMenu() //PASS
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
public void testsetNProductMenu() //PASS
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
public void testaddProductToOrderMenu() //PASS
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
public void testaddNProductToOrderMenu() //PASS
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
public void testdeleteProductFromOrderMenu() //PASS
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
public void deleteOrderlineFromOrderMenu() //PASS
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
}


	
	
	
	

