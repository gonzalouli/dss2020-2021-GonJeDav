package com.jedago.practica_dss.test;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jedago.practica_dss.core.Order;
import com.jedago.practica_dss.core.OrderLine;
import com.jedago.practica_dss.core.Product;


public class testOrder {
	double error = 0.001;
	private Product p;
	private OrderLine orderlineproduct;
	private Order order;
	@Before
	void setup() {
		order = new Order();
		Product p = new Product("producto",3, new BigDecimal("2,5") );
	}
	
	
	void teardown() {
		order = null;
		orderlineproduct = null ;
		p = null;
	}
	
	
	@Test
	public void testConstructor() {
		double cero =0 ;
		Date d = new Date();
		assert(order!=null);
		assertEquals( order.getDate(), d);
		assertEquals( order.getId_order()-1, Order.currentid);
		assertEquals(cero ,order.getPrice());
	}
	
	
	@Test
	public void testgetId_oder() {
		assertEquals( Order.currentid, order.getId_order()-1);
		
	}
	

	@Test
	public void testgetPrice() {
		BigDecimal priceinside = BigDecimal.ZERO;
		List<OrderLine> ol = order.getProducts();
		
		for(OrderLine pivot: ol) {
			priceinside.add(pivot.getTotalPrice());
			
		}
		assertEquals(priceinside, order.getPrice());
	}
	
	@Test
	public void testsetProduct() {
		
		orderlineproduct = new OrderLine(p,1);
		BigDecimal preprice = order.getPrice();
		
		order.setProduct( orderlineproduct );
		
		List<OrderLine> ol = order.getProducts();
		
		assert(ol.contains(orderlineproduct));
		
		preprice.add(orderlineproduct.getTotalPrice());
		assertEquals(order.getPrice(), preprice);
		
	}
	
	@Test
	public void testsetNProduct() {
		
		orderlineproduct = new OrderLine(p,3);
		BigDecimal preprice = order.getPrice();
		
		order.setProduct( orderlineproduct );
		
		List<OrderLine> ol = order.getProducts();
		
		assert(ol.contains(orderlineproduct));
		
		BigDecimal amount = BigDecimal.ZERO;
		amount = new BigDecimal(orderlineproduct.getAmount());
		preprice.add(orderlineproduct.getTotalPrice().multiply(amount));
		
		assertEquals(order.getPrice(), preprice);
		
	}
	
	
	@Test 
	void testaddProductToOrder(Product currProd) 
	{
		List<OrderLine> preol = order.getProducts();
		Boolean exit = true;
		int precant =0;
		
		for(int i = 0 ; i<preol.size() && exit ; i++)
			if(preol.get(i).getProduct().getID() == currProd.getID()) {
				precant = preol.get(i).getAmount();
				exit = false;
			}
				
		order.addProductToOrder(p); 
		
		List<OrderLine> ol = order.getProducts();
		exit = true;
		
		for(int i = 0 ; i<preol.size() && exit ; i++)
			if(ol.get(i).getProduct().getID() == currProd.getID()) {
				assertEquals(precant+1, ol.get(i).getAmount());
				assertEquals(ol.get(i).getTotalPrice(), ol.get(i).getProduct().getPriceUnit());
				exit = false;
			}
	}
		
	@Test	
	void testaddProductToOrder(Product currProd, int cant) 
	{
		List<OrderLine> preol = order.getProducts();
		Boolean exit = true;
		int precant =0;
		
		for(int i = 0 ; i<preol.size() && exit ; i++)
			if(preol.get(i).getProduct().getID() == currProd.getID()) {
				precant = preol.get(i).getAmount();
				exit = false;
			}
			
		order.addProductToOrder(p); 
		
		List<OrderLine> ol = order.getProducts();
		exit = true;
		
		for(int i = 0 ; i<preol.size() && exit ; i++)
			if(ol.get(i).getProduct().getID() == currProd.getID()) {
				assertEquals(precant+1, ol.get(i).getAmount());
				BigDecimal postamount = BigDecimal.ZERO;
				postamount = new BigDecimal(ol.get(i).getAmount());
				assertEquals(ol.get(i).getTotalPrice(), ol.get(i).getProduct().getPriceUnit().multiply(postamount));
				exit = false;
			}
	}
		
	@Test
	public void testdeteteProductFromOrder(Product currProd, int cant) {
		
		order.addProductToOrder(p);
		List<OrderLine> preol = order.getProducts();
		int precant =0;
		Boolean exit = true;
		int deletedindex = 0;
		
		for(int i = 0 ; i<preol.size() && exit ; i++)
			if(preol.get(i).getProduct().getID() == currProd.getID()) {
				precant = preol.get(i).getAmount();
				deletedindex = i;
				exit = false;
			}
		
		order.deteteProductFromOrder( currProd, cant);
		
		int postcant = 0;
		int i;
		exit = true;
		
		if( preol.get(deletedindex).getProduct().getID() == currProd.getID()) {
			for( i = 0 ; i<preol.size() && exit ; i++)
				if(preol.get(i).getProduct().getID() == currProd.getID()) {
					postcant = preol.get(i).getAmount();
					exit = false;
				}
		
			assertEquals(precant-cant,postcant);
		}else 
		{
			for( i = 0 ; i<preol.size() && exit ; i++)
				if(preol.get(i).getProduct().getID() == currProd.getID()) {
					exit = true;
					assert(!exit);
			}
		}
	}
	
	
	@Test
	public void deleteOrderlineFromOrder(OrderLine currentOrderLine) {
		Boolean exit = true;
		
		List<OrderLine> preol = order.getProducts();
		
		for(int i = 0 ; i<preol.size() && exit ; i++) {
			if(	preol.get(i).getProduct().getID() == currentOrderLine.getProduct().getID() ) {
				exit = false;
			}
			assert(exit);
		}
	}
	
}
	
	
	
	
	
	

