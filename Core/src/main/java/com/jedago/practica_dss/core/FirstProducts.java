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
		int id = 0;
		
		ProductType Complementos = new ProductType("Complemento");
		ProductType Bebidas = new ProductType("Bebidas");
		ProductType Dulces = new ProductType("Dulces");
		ProductType Bocadillos = new ProductType("Bocadillos");
		ProductType Postres = new ProductType("Postres");
		ProductType Menus = new ProductType("Menus");
		
		Product Patatas = new SingleProduct(++id, "Patatas fritas", 10, new BigDecimal(2), Complementos);
		Product CocaCola = new SingleProduct(++id, "CocaCola", 15, new BigDecimal(1), Bebidas);
		Product Cafe = new SingleProduct(++id, "Cafe", 30, new BigDecimal(0.75), Bebidas);
		Product Croissant = new SingleProduct(++id, "Croissant", 14, new BigDecimal(0.5), Dulces);
		Product Sandwich = new SingleProduct(++id, "Sandwich", 8, new BigDecimal(2.5), Bocadillos);
		Product Serranito = new SingleProduct(++id, "Serranito", 6, new BigDecimal(3.5), Bocadillos);
		Product Helado = new SingleProduct(++id, "Helado de chocolate", 8, new BigDecimal(1.5), Postres);
		
		//Constructor sin lista y se van añadiendo
		Menu MenuAlmuerzo = new Menu(++id, "Menu para almorzar", Menus);
		MenuAlmuerzo.add(CocaCola);
		MenuAlmuerzo.add(Serranito);
		MenuAlmuerzo.add(Patatas);
		
		//Genero el menu a partir de una lista
		List<Product> lp = new ArrayList<Product>();
		lp.add(Cafe);
		lp.add(Croissant);
		Product MenuDesayuno = new Menu(++id, "Menu para desayunar", Menus, lp);

		List<Product> ProductList =  new ArrayList<Product>();
		ProductList.add(MenuAlmuerzo);
		ProductList.add(MenuDesayuno);
		ProductList.add(Patatas);
		ProductList.add(CocaCola);
		ProductList.add(Cafe);
		ProductList.add(Croissant);
		ProductList.add(Sandwich);
		ProductList.add(Serranito);
		ProductList.add(Helado);

		
		return(ProductList);
	}

}
