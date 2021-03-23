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
	
	/** 
     * Assigns a name, stock, price unit and id
     * to a certain product
     * 
     **/
	public Product(String name, int stock, BigDecimal d) {
		super();
		this.name = name;
		this.stock = stock;
		this.priceunit = d;
		this.id = currentId;
		currentId++;
	}
	
	/** 
     * @returns the ID of  a product
     * 
     **/
	public int getID() {
		return id;
	}
	
	/** 
     * @returns the name of  a product
     * 
     **/
	public String getName() {
		return name;
	}
	
	/** 
     * @returns the price unit of a product
     * 
     **/
	public BigDecimal getPriceUnit() {
			return priceunit;
	}
	
	/** 
     * @returns the stock of a product
     * 
     **/
	public int getStock() {
		return stock;
	}
	
	/** 
     * Sets the price unit of a product
     * @param price_ new price of the selectec product.
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
	
	/** 
     * Compares if two products are the same
     * @param onj Object to compare
     **/
	
	@Override
	/** 
     * Sets the price unit of a product
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
