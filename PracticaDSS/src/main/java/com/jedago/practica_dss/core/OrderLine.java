package com.jedago.practica_dss.core;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A class to represent a line of a product
 * within the order. This class contains a product and its quantity
 * on the OrderLine.
 * @version 1.0. 21/03/2021
 * @author Gonzalo Ulibarri
 */

public class OrderLine implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4358074819046808171L;
	Product product;
	int amount;

 /** 
 * Create empty order with an uniquye id, a list empty of OrderLine and a given date.
 * @param product desired product to be entered in the OrderLine.
 * @param amount quantity to introduce of the product product.
 */
	public OrderLine(Product product, int amount) 
	{
		this.product = product;
		this.amount = amount;
	}
	
 /** 
 * Returns the product that belongs to a given OrderLine
 * @return Product of the OrderLine.
 */
	public Product getProduct() 
	{
		return product;
	}
	

 /** 
 * Enter a new product in a given OrderLine.
 * @param newproduct new product to introduce.
 */
	public void setProduct(Product newproduct) 
	{
		product = newproduct;
		
	}
	
 /** 
 * Returns a string that represents the name of the product
 * that belongs to a given OrderLine.
 * @return Product name.
 */	
	public String getProductName() 
	{
		return product.getName();
	}

 /** 
 * Returns the amount of product product that we have
 * in a given OrderLine.
 * @return Product quantity in the OrderLine.
 */
	public int getAmount() 
	{
		return amount;
	}
	
 /** 
 * Enter a new quantity of the product product
 * in a given OrderLine.
 * @param q New desired quantity of the product on the OrderLine.
 */
	public void setAmount(int q)
	{
		assert(q>=0);
		
		this.amount = q;
		
	}

 /** 
 * Returns the total cost of the amount of all of the
 * products within a given OrderLine.
 * @return Total price of the OrderLine.
 */
	public BigDecimal getTotalPrice() 
	{
		BigDecimal coste = BigDecimal.ZERO;
		BigDecimal costeTotal = BigDecimal.ZERO;
		coste = product.getPrice().multiply(new BigDecimal(amount));
		costeTotal = costeTotal.add(coste);
		return costeTotal;			
	}
	
	
	
	
	

}
