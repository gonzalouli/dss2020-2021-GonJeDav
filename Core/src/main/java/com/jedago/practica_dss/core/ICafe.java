/**
 * 
 */
package com.jedago.practica_dss.core;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

import com.jedago.practica_dss.core.exceptions.NoStockException;

/**
 * @author Jesús Serrano Gallán
 *
 */
public interface ICafe {
	
	/**
	 * Create a new order
	 * @return the new ordenr created
	 */
	public Order newOrder();
	
	/**
	 * Set the desired collection time for the order
	 * @param o Order to set the collection time
	 * @param t Time to collect the order
	 */
	public void setCollectTime(Order o, LocalDateTime t);
	
	/**
	 * Returns a set of available products
	 * @return List with the availables products
	 */
	public List<Product> getAvailableProducts();
	
	/**
	 * Returns a set of available product types
	 * @return List with the availables product types
	 */
	public List<ProductType> getAvailableProductTypes();
	
	
	/**
	 * Returns a set of available products of an specified type
	 * @param t type of the products you want to get
	 * @return List with the availables products
	 */
	public List<Product> getAvailableProductsbyType(ProductType t);
	
	/**
	 * Returns a set of registered orders
	 * @return List with the registered orders
	 */
	public List<Order> getRegisteredOrders();
	
	/**
	 * To add a unit of a product to an order
	 * @param ord the order in which you want to add the product
	 * @param p the product which you want to be added
	 */
	public void addProductToOrder(Order ord, Product p) throws NoStockException;
	
	/**
	 * To add several units of a product to an order
	 * @param ord the order in which you want to add the product
	 * @param p the product which you want to be added
	 * @param c the quantity of the product
	 */
	public void addProductToOrder(Order ord, Product p, int c) throws NoStockException;
	
	/**
	 * To delete a unit of a product from an order
	 * @param ord the order in which you want to add the product
	 * @param p the product which you want to be added
	 */ 
	public void deleteProductFromOrder(Order ord, Product p);
	
	/**
	 * To delete several unit of a product from an order
	 * @param ord the order in which you want to add the product
	 * @param p the product which you want to be added
	 * @param c the quantity of the product you wannt to delete 
	 */
	public void deleteProductFromOrder(Order ord, Product p, int c);
	
	/**
	 * Register the order to finish it
	 * @param ord the order you want to register
	 */
	public void FinishOrder(Order ord) throws Exception;
	
	/**
	 * Returs the amount of registered orders and the money earned in a day
	 * @return a CashBox with the amount of orders registered and the money earned
	 * @param date The date of the CashBox you want to check
	 */
	public CashBox getCashBox(LocalDate date);
	
	/**
	 * Returs the amount of registered orders and the money earned today
	 * @return a CashBox with the amount of orders registered and the money earned
	 */
	public CashBox getTodayCashBox();
}
