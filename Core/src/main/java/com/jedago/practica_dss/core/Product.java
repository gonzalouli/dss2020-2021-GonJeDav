/**
 * 
 */
package com.jedago.practica_dss.core;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Interface to provide the methods which can be applied from any type of Product
 * @author Jesús Serrano Gallán
 *
 */
public interface Product extends Serializable{
	/**
	 * @return the type.
	 */
	public ProductType getType();
	
	/**
	 * @param type the type to set.
	 */
	public void setType(ProductType type);
	
	/** 
     * @return The ID of  a product.
     * 
     **/
	public String getID();
	
	/** 
     * @return The name of  a product.
     * 
     **/
	public String getName();
	
	/** 
     * @return The price of a product.
     * 
     **/
	public BigDecimal getPrice();
	
	/** 
     * @return The stock of a product.
     * 
     **/
	public int getStock();
	
	/**
	 * To set the stock of a product
	 * @param stock Set the stock of the product.
	 */
	public void setStock(int stock);


}
