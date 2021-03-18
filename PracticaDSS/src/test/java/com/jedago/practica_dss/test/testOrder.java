package com.jedago.practica_dss.test;
import static org.junit.Assert.*;

import java.util.Date;

import  org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jedago.practica_dss.core.Order;
import com.jedago.practica_dss.core.OrderLine;


public class testOrder {

	private OrderLine orderlineproduct;
	Order order;
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

		Date d = new Date();
		assert(order!=null);
		assertEquals("Fecha Incorrecta", order.getDate(), d);
		assertEquals("ID incorrecto", order.getId_oder(), Order.currentid);
		assertEquals("ResetearPrecio", order.getPrice(), 0);
		assertEquals("ResetearPrecio", order.getPrice(), 0))
	
	}
	
	
	
	

}
