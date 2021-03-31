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
	private BigDecimal 	priceUnit;
	private static	int currentId=1;
	private ProductType type; 
	
	/** 
     * Assigns a name, stock, price unit and id
     * to a certain product.
     * @param name Name of the new product.
     * @param stock Stock of the new product, represents the quantity of product available.
     * @param priceunit Represents the price of a single unit of the product .
     * @param type ProductType of the product that we are crating .
     **/
	public Product(String name, int stock, BigDecimal priceunit, ProductType type) {
		super();
		this.name = name;
		this.stock = stock;
		this.priceUnit = priceunit;
		this.id = currentId;
		this.type = type;
		currentId++;
	}
	
	/**
	 * @return the type.
	 */
	public ProductType getType() {
		return type;
	}

	/**
	 * @param type the type to set.
	 */
	public void setType(ProductType type) {
		this.type = type;
	}

	/**
	 * @return the currentId.
	 */
	public static int getCurrentId() {
		return currentId;
	}
	
	/** 
     * @return The ID of  a product.
     * 
     **/
	public int getID() {
		return id;
	}
	
	/** 
     * @return The name of  a product.
     * 
     **/
	public String getName() {
		return name;
	}
	
	/** 
     * @return The price of a product.
     * 
     **/
	public BigDecimal getPriceUnit() {
			return priceUnit;
	}
	
	/** 
     * @return The stock of a product.
     * 
     **/
	public int getStock() {
		return stock;
	}
	
	/** 
     * Sets the price unit of a product.
     * @param price_ New price of the selected product.
     **/
	public void setPriceUnit(BigDecimal price_) {
		this.priceUnit = price_;
	}
	
	/** 
     * Sets the stock of a product.
     * @param stock_ New stock of the product.
     **/
	public void  setStock(int stock_) {
		this.stock = stock_;
	}
	
	
	/** 
     * Sets the type of a product.
     * @param newType New type of the product.
     **/
	public void setTypeProduct(ProductType newType) {
		type = newType;
	}
	
	/** 
     * Sets the stock of a product
     * @return Return the type of the product.
     **/
	public ProductType getTypeProduct() {
		return type;
	}
	
	
	@Override
	/** 
     * Compares if two products are the same.
     * @param obj_ Object to compare.
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
