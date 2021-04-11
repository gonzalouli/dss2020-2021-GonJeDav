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
		Product patata = new Product("Patatas fritas", 2, new BigDecimal(2), new ProductType("Patatas"));
		Product cocacola = new Product("Cocacola", 5, new BigDecimal(1.5), new ProductType("Bebidas"));
		Product bocadillo = new Product("Ponty de pollo", 4, new BigDecimal(3), new ProductType("Bocadillos"));

		ProductList.add(patata);
		ProductList.add(cocacola);
		ProductList.add(bocadillo);

		
		return(ProductList);
	}

}
