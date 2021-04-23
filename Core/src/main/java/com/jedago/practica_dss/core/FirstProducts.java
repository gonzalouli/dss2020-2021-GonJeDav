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
		int id_products = 0;
		int id_types = 0;
		
		ProductType Complementos = new ProductType(id_types++, "Complemento");
		ProductType Bebidas = new ProductType(id_types++,"Bebidas");
		ProductType Dulces = new ProductType(id_types++,"Dulces");
		ProductType Bocadillos = new ProductType(id_types++,"Bocadillos");
		ProductType Postres = new ProductType(id_types++,"Postres");
		ProductType Menus = new ProductType(id_types++,"Menus");
		
		Product Patatas = new SingleProduct(++id_products, "Patatas fritas", 10, new BigDecimal(2), Complementos);
		Product CocaCola = new SingleProduct(++id_products, "CocaCola", 15, new BigDecimal(1), Bebidas);
		Product Cafe = new SingleProduct(++id_products, "Cafe", 30, new BigDecimal(0.75), Bebidas);
		Product Croissant = new SingleProduct(++id_products, "Croissant", 14, new BigDecimal(0.5), Dulces);
		Product Sandwich = new SingleProduct(++id_products, "Sandwich", 8, new BigDecimal(2.5), Bocadillos);
		Product Serranito = new SingleProduct(++id_products, "Serranito", 6, new BigDecimal(3.5), Bocadillos);
		Product Helado = new SingleProduct(++id_products, "Helado de chocolate", 8, new BigDecimal(1.5), Postres);
		
		//Constructor sin lista y se van añadiendo
		Menu MenuAlmuerzo = new Menu(++id_products, "Menu para almorzar", Menus);
		MenuAlmuerzo.add(CocaCola);
		MenuAlmuerzo.add(Serranito);
		MenuAlmuerzo.add(Patatas);
		
		//Genero el menu a partir de una lista
		List<Product> lp = new ArrayList<Product>();
		lp.add(Cafe);
		lp.add(Croissant);
		Product MenuDesayuno = new Menu(++id_products, "Menu para desayunar", Menus, lp);

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
