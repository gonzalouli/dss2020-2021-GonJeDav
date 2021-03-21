package com.jedago.practica_dss.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jedago.practica_dss.core.Order;
import com.jedago.practica_dss.core.OrderLine;
import com.jedago.practica_dss.core.Product;


public class testOrder {
	double error = 0.001;
	private int cant;
	double cash = 2.5;
	private Product p;
	private OrderLine orderlineproduct;
	private Order order;
	
	private List<OrderLine> ol;
	
	@Before
	public void setup() 
	{
		
		BigDecimal precio = BigDecimal.valueOf(cash);
		p = new Product("producto", 4, precio);
		orderlineproduct = new OrderLine(p,cant);
		
		ol = new ArrayList<OrderLine>();
		order = new Order();		
		
		ol.add(orderlineproduct);
	}
	
	@After
	public void teardown() 
	{
		order = null;
		orderlineproduct = null ;
		p = null;
	}
	
	
	@Test
	public void testConstructor() //PASS
	{ 
		
		BigDecimal cero =  BigDecimal.ZERO ;
		Date d = new Date();
		//assert(order!=null);
		
		assertEquals( order.getDate(), d);
		assertEquals( order.getId_order(), Order.currentid-1);
		assertEquals(cero ,order.getPrice());
}


	@Test
	public void testgetId_oder() //PASS
	{ 
		Order orden = new Order();
		assertEquals( Order.currentid -1, orden.getId_order());
		
	}
//	

	@Test
	public void testgetPrice() //PASS
	{

		order.setProducts(orderlineproduct);

		BigDecimal priceinside = BigDecimal.ZERO;
		
		for(int i=0 ; i<order.size() ; i++)
			ol.add(i,order.getProducts().get(i));
		
		
		System.out.print(order.size());

		for(int i=0 ; i<order.size() ; i++)
			priceinside = priceinside.add(ol.get(i).getTotalPrice());
		
		assert(priceinside.equals(order.getPrice()));
		
	}
	
	
	@Test
	public void testsetProduct() //PASS
	{
		
		orderlineproduct = new OrderLine(p,1);
		BigDecimal preprice = order.getPrice();
		
		order.setProducts( orderlineproduct );
		
		List<OrderLine> ol = order.getProducts();
		
		assert(ol.contains(orderlineproduct));
		
		preprice = preprice.add(orderlineproduct.getTotalPrice());
		assertEquals(order.getPrice(), preprice);
		
	}
	
	@Test
	public void testsetNProduct() //PASS
	{
		
		BigDecimal preprice = order.getPrice();
		
		order.setProducts( orderlineproduct );
		
		List<OrderLine> ol = order.getProducts();
		Boolean notin=true;
		
		for(int i=0 ; i<ol.size() && notin==true ; i++) {
			if(ol.get(i).getProduct().getID() == p.getID()) {
				notin =false;
			}
		}
		
		assert(!notin);
		
		BigDecimal amount = BigDecimal.valueOf(orderlineproduct.getAmount());
		
		preprice = preprice.add(orderlineproduct.getTotalPrice().multiply(amount));
		
		assertEquals(order.getPrice(), preprice);
		
	}
	
	
	@Test 
	public void testaddProductToOrder() //PASS
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
				assertEquals(ol.get(i).getTotalPrice(), ol.get(i).getProduct().getPriceUnit());
				exit = false;
			}
	}
		
	@Test	
	public void testaddNProductToOrder() //PASS
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
				assertEquals(ol.get(i).getTotalPrice(), ol.get(i).getProduct().getPriceUnit().multiply(postamount));
				exit = false;
			}
	}
		
	@Test
	public void testdeleteProductFromOrder() //PASS
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
	public void deleteOrderlineFromOrder() //PASS
	{
		Boolean exit = true;
		
		List<OrderLine> preol = order.getProducts();
		
		for(int i = 0 ; i<preol.size() && exit ; i++) {
			if(	preol.get(i).getProduct().getID() == orderlineproduct.getProduct().getID() ) {
				exit = false;
			}
			assert(exit);
		}
	}
}
	
	
	
	
	
	

