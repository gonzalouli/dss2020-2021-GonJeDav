package com.jedago.practica_dss.core;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
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
	
	Product product_;
	int amount;

 /** 
 * Create empty order with an uniquye id, a list empty of OrderLine and a given date.
 * @param product_ desired product to be entered in the OrderLine.
 * @param amount quantity to introduce of the product product_.
 */
	public OrderLine(Product product_, int amount) 
	{
		this.product_ = product_;
		this.amount = amount;
	}
	
 /** 
 * Returns the product that belongs to a given OrderLine
 * @return Product of the OrderLine.
 */
	public Product getProduct() 
	{
		return product_;
	}
	

 /** 
 * Enter a new product in a given OrderLine.
 * @param newproduct new product to introduce.
 */
	public void setProduct(Product newproduct) 
	{
		product_ = newproduct;
		
	}
	
 /** 
 * Returns a string that represents the name of the product
 * that belongs to a given OrderLine.
 * @return Product name.
 */	
	public String getProductName() 
	{
		return product_.getName();
	}

 /** 
 * Returns the amount of product product_ that we have
 * in a given OrderLine.
 * @return Product quantity in the OrderLine.
 */
	public int getAmount() 
	{
		return amount;
	}
	
 /** 
 * Enter a new quantity of the product product_
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
		coste = product_.getPriceUnit().multiply(new BigDecimal(amount));
		costeTotal = costeTotal.add(coste);
		return costeTotal;			
	}
	
	
	
	
	

	public void writeObject() 
	{
		try {
			
			ObjectOutputStream writeObjectOnFile = new ObjectOutputStream(new FileOutputStream("../serialization/orderline.txt"));
			writeObjectOnFile.writeObject(this);
		}catch(Exception e) {}
		
	}

}
