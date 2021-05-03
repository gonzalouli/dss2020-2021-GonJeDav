package com.jedago.practica_dss.core;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * A class to represent the details of the product.
 * Each product has a name, id, stock, price unit and type
 * @version 2.0. 11/04/2021
 * @author Jesús Serrano Gallán
 */
public class SingleProduct implements Product{
	
	private static final long serialVersionUID = 2836988550921564899L;
	private String	name;
	private String		id; 
	private int 	stock;
	private BigDecimal 	priceUnit;
	private ProductType type; 
	
	public SingleProduct() {
		super();
		this.id = UUID.randomUUID().toString();
		}
	
	/** 
     * Assigns a name, stock, price unit and id
     * to a certain product.
     * @param id Id for the product
     * @param name Name of the new product.
     * @param stock Stock of the new product, represents the quantity of product available.
     * @param priceunit Represents the price of a single unit of the product .
     * @param type ProductType of the product that we are crating .
     **/
	public SingleProduct( String name, int stock, BigDecimal priceunit, ProductType type) {
		this.name = name;
		this.stock = stock;
		this.priceUnit = priceunit;
		this.type = type;
		this.id = UUID.randomUUID().toString();

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
     * @return The ID of  a product.
     * 
     **/
	public String getID() {
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
     * @return The stock of a product.
     * 
     **/
	public int getStock() {
		return stock;
	}
	
	@Override
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	/** 
     * @return The price of a product.
     * 
     **/
	public BigDecimal getPrice() {
			return priceUnit;
	}
	
	/** 
     * Sets the price unit of a product.
     * @param price New price of the selected product.
     **/
	public void setPrice(BigDecimal price) {
		this.priceUnit = price;
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
        
        if (id != other.getID()) 
        	return false;
        
        return true;
    }
	
}


