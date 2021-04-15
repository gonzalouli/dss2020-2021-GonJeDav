/**
 * 
 */
package com.jedago.practica_dss.persistance;

import java.util.List;

import com.jedago.practica_dss.core.Order;

/**
 * @author Jesus Serrano Gallan
 *
 */
public interface OrdersRepository {
	/**
	 * To get the list of registered orders
	 * @return A List with all the registered Orders
	 * @throws Exception
	 */
	public List<Order> readOrders() throws Exception;
	
	/**
	 * To save the orders to the repository
	 * @param OrderList the OrderList we wan to save
	 * @throws Exception
	 */
	public void writeOrders(List<Order> OrderList) throws Exception;
}
