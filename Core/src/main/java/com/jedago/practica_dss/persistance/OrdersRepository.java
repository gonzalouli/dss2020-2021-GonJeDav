/**
 * 
 */
package com.jedago.practica_dss.persistance;

import java.util.List;
import java.util.Optional;

import com.jedago.practica_dss.core.Order;
import com.jedago.practica_dss.core.User;

/**
 * @author Jesus Serrano Gallan
 *
 */
public interface OrdersRepository {
	
	/**
	 * To get the list of all registered orders
	 * @return A List with all the registered Orders
	 * @throws Exception
	 */
	public List<Order> findAll() throws Exception;
	
	/**
	 * To get an order from its id
	 * @param id
	 * @return The order with that id
	 * @throws Exception
	 */
	public Optional<Order> findById(int id) throws Exception;
	
	/**
	 * To save the orders to the repository, overwriting the previous ones
	 * @param orderList the OrderList we want to save
	 * @throws Exception
	 */
	public void save(List<Order> orderList) throws Exception;
	
	
	/**
	 * To save just one order in the repository, adding it to the previous one
	 * @param o order to save
	 * @throws Exception
	 */
	public void add(Order o) throws Exception;
	
	/**
	 * To delete several orders from the repository
	 * @param orderList orders to delete
	 * @throws Exception
	 */
	public void delete(List<Order> orderList) throws Exception; 
	
	/**
	 * To delete an order from the repository
	 * @param o order to delete
	 * @throws Exception
	 */
	public void delete(Order o) throws Exception; 
	
	/**
	 * To add an user to an existing order
	 * @param u user to add 
	 * @param o order to update
	 * @throws Exception
	 */
	public void addUsertoOrder(User u, Order o) throws Exception;
}
