package com.jedago.practica_dss.test;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import  org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jedago.practica_dss.core.Order;
import com.jedago.practica_dss.core.OrderLine;
import com.jedago.practica_dss.core.Product;


public class testOrder {
	double error = 0.001;

	private OrderLine orderlineproduct;
	private Order order;
	@Before
	void setup() {
		order = new Order();
	}
	
	
	void teardown() {
		order = null;
		orderlineproduct = null ;
	}
	
	
	@Test
	public void testConstructor() {
		double cero =0 ;
		Date d = new Date();
		assert(order!=null);
		assertEquals("Fecha Incorrecta", order.getDate(), d);
		assertEquals("ID incorrecto", order.getId_order()-1, Order.currentid);
		assertEquals("ResetearPrecio", cero ,order.getPrice(), error);
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
		
		Product p = new Product("producto",3,2.5);
		orderlineproduct = new OrderLine(p,1);
		double preprice = order.getPrice();
		
		order.setProduct( orderlineproduct );
		
		List<OrderLine> ol = order.getProducts();
		
		assert(ol.contains(orderlineproduct));
		
		preprice += orderlineproduct.getTotalPrice();
		assertEquals("No se actualizan bien los precios",order.getPrice(), preprice,error);
		
	}
	
	@Test
	public void testsetNProduct() {
		
		Product p = new Product("producto",3,2.5);
		orderlineproduct = new OrderLine(p,3);
		double preprice = order.getPrice();
		
		order.setProduct( orderlineproduct );
		
		List<OrderLine> ol = order.getProducts();
		
		assert(ol.contains(orderlineproduct));
		
		preprice += orderlineproduct.getTotalPrice()*orderlineproduct.getAmount();
		
		assertEquals("No se actualizan bien los precios",order.getPrice(), preprice, error);
		
	}
	
	
	@Test void testaddProductToOrder(Product currProd) 
	{
		Product p = new Product("producto",3,2.5);
		List<OrderLine> preol = order.getProducts();
		int precant =0;
		for(OrderLine prepivot : preol) 
			if(prepivot.getProduct().getID() == currProd.getID()) {
				precant = prepivot.getAmount();
				break;
			}
				
		order.addProductToOrder(p); 
		
		List<OrderLine> ol = order.getProducts();

		for(OrderLine pivot : ol) {
			if(pivot.getProduct().getID() == currProd.getID()) {
				assertEquals(precant+1, pivot.getAmount());
				assertEquals(pivot.getTotalPrice(), pivot.getProduct().getPriceUnit(), error);
			}
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
