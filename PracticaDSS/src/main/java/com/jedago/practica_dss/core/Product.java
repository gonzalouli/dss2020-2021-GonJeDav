package com.jedago.practica_dss.core;

import java.math.BigDecimal;

/**
 * A class to represent the details of the product.
 * Each product has a name, id, stock and price unit
 * @version 1.0. 23/03/2021
 * @author Javier David García-Pardo Montero
 */


public class Product{
	private String	name;
	private int		id;
	private int 	stock;
	private BigDecimal 	priceunit;
	public static	int currentId=1;
	//Add categoria: string, numerado, clase de tipo de producto
	//ProductType t; 
	
	/** 
     * Assigns a name, stock, price unit and id
     * to a certain product
     * @param name Name of the new product.
     * @param stock Stock of the new product, represents the quantity of product available.
     * @param priceunit Represents the price of a single unit of the product 
     * 
     **/
	public Product(String name, int stock, BigDecimal priceunit) {
		super();
		this.name = name;
		this.stock = stock;
		this.priceunit = priceunit;
		this.id = currentId;
		currentId++;
	}
	
	/** 
     * @return The ID of  a product
     * 
     **/
	public int getID() {
		return id;
	}
	
	/** 
     * @return The name of  a product
     * 
     **/
	public String getName() {
		return name;
	}
	
	/** 
     * @return The price of a product
     * 
     **/
	public BigDecimal getPriceUnit() {
			return priceunit;
	}
	
	/** 
     * @return The stock of a product
     * 
     **/
	public int getStock() {
		return stock;
	}
	
	/** 
     * Sets the price unit of a product
     * @param price_ New price of the selected product.
     **/
	public void setPriceUnit(BigDecimal price_) {
		this.priceunit = price_;
	}
	
	/** 
     * Sets the stock of a product
     * @param stock_ New stock of the product
     **/
	public void  setStock(int stock_) {
		this.stock = stock_;
	}
	
	
	@Override
	/** 
     * Compares if two products are the same
     * @param obj_ Object to compare
     * @return If the object are the same return true, else return false.
     **/
    public boolean equals(Object obj) {
        if (this == obj) 
        	return true;
        if (obj == null) 
        	return false;
        if (getClass() != obj.getClass()) 
        	return false;
        
        final Product other = (Product)obj;
        
        if (id != other.id) 
        	return false;
        
        return true;
    }
}
