package com.jedago.practica_dss.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.jedago.practica_dss.core.Order;
import com.jedago.practica_dss.core.OrderLine;
import com.jedago.practica_dss.core.Product;
import com.jedago.practica_dss.core.ProductType;

public class pruebaOrder {

	public static void main(String[] args) {
		
		Order order = new Order();		
		ProductType t = new ProductType("cafe");
		int cash = 3;
		BigDecimal precio = BigDecimal.valueOf(cash);
		Product p = new Product("producto", 4, precio, t );
		//OrderLine orderlineproduct = new OrderLine(p,4);
		List<OrderLine> ol = new ArrayList<OrderLine>();
		//order.setProducts(orderlineproduct);
		order.addProductToOrder(p, 4);
		 
		ol = order.getProducts();
		
		System.out.println(ol.get(0).getAmount());
		System.out.println(ol.get(0).getProductName());
		System.out.println(ol.get(0).getTotalPrice());
		System.out.println(ol.get(0).getProduct().getStock());
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		order.deleteProductFromOrder(p, 4);
		
		ol = order.getProducts();
		System.out.println(order.getPrice());
		System.out.println(ol.isEmpty());

	}

}
