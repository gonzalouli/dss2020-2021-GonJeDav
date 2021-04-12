/**
 * 
 */
package com.jedago.practica_dss.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 12/4/21
 * A class to represent the details of a menu compound by several products
 * @author Jesús Serrano Gallán
 *
 */
public class Menu implements Product {
	
	private static final long serialVersionUID = 9051623461335977451L;
	private int		id;
	private String	name;
	private ProductType type; 
	private List<Product> components;
	
	/**
	 * Constructor for a void menu
	 * @param id
	 * @param name
	 * @param type
	 */
	public Menu(int id, String name, ProductType type) {
		this.id = id;
		this.name = name;
		this.type = type;
		components = new ArrayList<Product>();
	}
	
	/**
	 * Constructor for a menu with some products
	 * @param id
	 * @param name
	 * @param type
	 * @param lp
	 */
	public Menu(int id, String name, ProductType type, List<Product> lp) {
		this.id = id;
		this.name = name;
		this.type = type;
		components = lp;
	}
	
	/**
	 * To add a Product to the menu
	 * @param p The product to add
	 */
	public void add(Product p)
	{
		components.add(p);
	}
	
	/**
	 * To remove a Product from the menu
	 * @param p Thre product to remove
	 */
	public void remove(Product p)
	{
		components.remove(p);
	}
	
	/**
	 * To get the SingleProduct list that makes the menu
	 */
	public List<Product> getComponents()
	{
		return(components);
	}

	@Override
	public ProductType getType() {
		return type;
	}

	@Override
	public void setType(ProductType type) {
		this.type = type;
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public String getName() {
		String menu = name + ": ";
		
		for(Product p : components)
		{
			menu += p.getName() + " + ";
		}
		
		menu = menu.substring(0,menu.length()-3);
		
		return menu;
	}

	@Override
	public int getStock() {
		int min = Integer.MAX_VALUE;
		
		for(Product p : components)
		{
			if(min > p.getStock())
				min = p.getStock();
		}
		
		return min;
	}
	
	@Override
	public void setStock(int newStock) {
		
		int currentStock = this.getStock();
		int exceso;
		for(Product p : components)
		{
			if(p.getStock()<=currentStock)
				p.setStock(newStock);
			else
			{
				exceso = p.getStock() - currentStock;
				p.setStock(newStock + exceso);
			}
		}
	}

	@Override
	public BigDecimal getPrice() {
		BigDecimal price = new BigDecimal(0);
		
		for(Product p : components)
		{
			price = price.add(p.getPrice());
		}
		
		return price;
	}

}
