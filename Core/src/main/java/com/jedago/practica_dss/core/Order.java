package com.jedago.practica_dss.core;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * A class to represent an order placed in
 * the cafe class. The order is determined by a
 * id, a list of OrderLine, a total price
 * of the order and an opening date.
 * @version 1.0. 21/03/2021
 * @author Gonzalo Ulibarri
 */

public class Order implements Iterable<OrderLine>, Serializable
{
	private static final long serialVersionUID = -7323804151378580352L;
	
	public static long currentid = 1;
	private long id_order;
	private List<OrderLine> orderLineProduct;
	private BigDecimal price;
	private LocalDateTime date; //Local time, local date, localdatetime
	private LocalDateTime PickUpTime;
	private User user;
	
	
	 /*
	 * Create empty order with a unique id, a list
	 * of OrderLine empty and a given collected date.
	 */	
	
	public Order(){
		
		date = LocalDateTime.now();
		this.id_order = currentid;
		currentid++;
		this.price = BigDecimal.ZERO;
		orderLineProduct = new ArrayList<OrderLine>();
		
	}	
	
	
	 /** 
		 * Returns the id of a specific order.
		 * @return The collected date of the order.
		 */
	public LocalDateTime getPickUpTime() {
		return PickUpTime;
	}
	
	
	/** Set the collected date
	 * @param ldt Date to collect the order
	 */
	public void setPickUpTime(LocalDateTime ldt) 
	{
		PickUpTime = ldt;
	}
	


	 /** 
	 * Returns the id of a specific order.
	 * @return The order id.
	 */
	public long getId_order() {
		return this.id_order;
	}
	
	
	 /** 
	 * Returns the price of a specific order.
	 * @return Order's price.
	 */
	public BigDecimal getPrice() {
		return this.price;
	}
	
	 /** 
	 * Check the date the order was created.
	 * @return Order's id.
	 */
	public LocalDate getDate() {
		return this.date.toLocalDate();
	}

	
	
	 /** 
	 * Check the date the order was created.
	 * @return Order's id.
	 */
	public LocalDateTime getDateTime() {
		return this.date;
	}	
	
	
	
 /** 
 * Indicates if the order is empty.
 * @return True if the order is empty, false otherwise.
 */
	public boolean isEmpty() {
		return orderLineProduct.isEmpty();
	}

 /** 
 * Returns the size of a given order.
 * @return The size of an order.
 */
	public int size() {
		return orderLineProduct.size();
	}
	
	
 /** 
 * Returns the list of OrderLine that contains the product.
 * @return Product OrderLine list.
	 */	public List<OrderLine> getProducts() 
	{
		return this.orderLineProduct;
	}
	
	 
 /** 
 * Enter an OrderLine in the order.
 * @param currentOrderLine OrderLine to be entered in the order.
 */
	public void setProducts(OrderLine currentOrderLine ) 
	{	
		this.orderLineProduct.add(currentOrderLine);
		BigDecimal namount = BigDecimal.valueOf(currentOrderLine.getAmount());
		BigDecimal total = currentOrderLine.getProduct().getPrice().multiply(namount);
		this.price = this.price.add(total);
	}

	
	 /** 
     * Enter a unit of a specific product in the order.
	 * @param currentProduct defines the product that we want to introduce in the order.
	 */
	public void addProductToOrder(Product currentProduct)
	{	
		/*for(OrderLine pivot : OrderLineProduct) {
			if(pivot.getProduct().getID() == currentProduct.getID()) {
				pivot.setAmount(pivot.getAmount()+1);
				this.price = this.price.add(pivot.getProduct().getPriceUnit());				
				return;
			}
		}
		
		OrderLine ol = new OrderLine(currentProduct,1);
		setProducts(ol);	*/
		this.addProductToOrder(currentProduct, 1);
		
	}
	
	 /** 
     * Enter a certain number of units of the product in the order.
	 * @param currentProduct defines the product that we want to introduce in the order.
	 * @param cant defines the amount of product we want to introduce.
	 */
	public void addProductToOrder(Product currentProduct , int cant)
	{	
		for(OrderLine pivot : orderLineProduct) {
			if(pivot.getProduct().getID() == currentProduct.getID()) {
				pivot.setAmount(pivot.getAmount()+cant);
				BigDecimal namount = new BigDecimal(pivot.getAmount()) ;
				this.price = this.price.add(pivot.getProduct().getPrice().multiply(namount));
				return;
			}
		}

		OrderLine ol = new OrderLine(currentProduct,cant);
		setProducts(ol);
	}
	
	 /** 
     * Delete a specified quantity of a given product in the order. If the quantity
     * is greater than or equal to the current quantity of the product in the OrderLine
     * removes the product from the OrderLine list.
	 * @param currentProduct defines the product that we want to remove from the order.
	 * @param cant defines the quantity of the product to be eliminated.
	 */
	public void deleteProductFromOrder(Product currentProduct, int cant) {
		
		///for(OrderLine pivot : OrderLineProduct) {
		//OrderLineProduct.get(i)
		OrderLine ol;
		Iterator<OrderLine> it = orderLineProduct.iterator();
		while(it.hasNext()) { 
			ol = it.next();
			if(ol.getProduct().getID() == currentProduct.getID()) {
				if(cant>0) {
					BigDecimal newamount = new BigDecimal(ol.getAmount()-cant);

					if(cant >= ol.getAmount() ) {

						this.price = this.price.subtract(ol.getProduct().getPrice().multiply(new BigDecimal(ol.getAmount())));
						deleteOrderlineFromOrder(it);
						
					}else {
						
						ol.setAmount( newamount.intValue());
						this.price = this.price.subtract(ol.getProduct().getPrice().multiply(new BigDecimal(cant)));
						
					}
				}
			}
		}	
	}
	
	 /** 
	 * Removes a given OrderLine from the OrderLine list of the order.
	 * @param currentOrderLine defines the OrderLine that we want to remove from the order.
	 */
	public void deleteOrderlineFromOrder(OrderLine currentOrderLine) 
	{
		orderLineProduct.remove(currentOrderLine);
		
	}
	
	/** 
	 * Remove a given OrderLine from the OrderLine list of the order using an iterator.	 
	 * @param it Iterator that sets a given OrderLine in the list of
	 * OrderLine.
	 */
	public void deleteOrderlineFromOrder(Iterator<OrderLine> it) 
	{
		it.remove();
		
	}
	
	/** 
	 * Returns an iterator of type OrderLine.
	 * @return OrderLineProduct iterator, a list of OrderLine.
	 */
	
	@Override
	public Iterator<OrderLine> iterator() {
		return orderLineProduct.iterator();
	}


	/** 
	 * Returns the owner to this order
	 * @return user return the user of this order.
	 */
	public User getUser() {
		return user;
	}
	
	
	/** 
	 * Bind an user to an order.
	 * @param user User to bing with a order
	 */
	public void setUser(User user) {
		this.user = user;
	}
	

}
	


