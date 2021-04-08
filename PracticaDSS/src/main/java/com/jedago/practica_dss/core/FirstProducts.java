/**
 * 
 */
package com.jedago.practica_dss.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jesús Serrano Gallán
 *
 */
public class FirstProducts {

	public static List<Product> getFirstProducts()
	{
		List<Product> ProductList =  new ArrayList<Product>();
		Product p = new Product("patata", 2, new BigDecimal(3), new ProductType("Rico"));
		ProductList.add(p);
		
		return(ProductList);
	}

}
