/**
 * 
 */
package com.jedago.practica_dss.core;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.jedago.practica_dss.core.exceptions.NoStockException;

/**
 * @author Jesús Serrano Gallán
 *
 */
public interface ICafe {
	
	/**
	 * Create a new order
	 * @return the new order created
	 */
	public Order newOrder();
	
	/**
	 * Create a new order bind to an user
	 * @param u the user to bind the new order
	 * @return the new order created
	 */
	public Order newOrder(User u);
	
	/**
	 * Create a new order bind to an user by its id
	 * @param u the id of the user to bind the new order
	 * @return the new order created
	 */
	public Order newOrder(String uid);
	
	/**
	 * Set the desired collection time for the order
	 * @param o Order to set the collection time
	 * @param t Time to collect the order
	 */
	public void setPickUpTime(Order o, LocalDateTime t);
	
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
	 * Returns a set of available products of an specified type
	 * @param id ID of the type of the products you want to get
	 * @return List with the availables products
	 */
	public List<Product> getAvailableProductsbyType(String id);
	
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
	public CashBox getTodayCashBox() ;
	
	/**
	 * To register a new user in the system
	 * @param u The new user in the system
	 * @retun the id of the registered user
	 */
	public String registerUser(User u) throws Exception;
	
	/**
	 * To update the first name of an existing user
	 * @param u The existing user to update
	 * @param newFirstName The new first name to save
	 */
	public void updateUserFirstName(User u, String newFirstName) throws Exception;
	
	/**
	 * To update the last name of an existing user
	 * @param u The existing user to update
	 * @param newLastName The new last name to save
	 */
	public void updateUserLastName(User u, String newLastName) throws Exception;
	
	/**
	 * To update the birthdate of an existing user
	 * @param u The existing user to update
	 * @param newBirthDate The new birth date to save
	 */
	public void updateUserBirthDate(User u, LocalDate newBirthDate) throws Exception;
	
	/**
	 * To update the dni of an existing user
	 * @param u The existing user to update
	 * @param newDNI The new DNI to save
	 */
	public void updateUserDNI(User u, String newDNI) throws Exception;
	
	/**
	 * To get the list of registered users
	 * @return The list of registered users
	 */
	public List<User> getRegisteredUsers();
	
	/**
	 * To get the orders of an user
	 * @param u User 
	 * @return The list of orders from the user
	 */
	public List<Order> getUserOrders(User u);
	
	/**
	 * To get an specified user by id
	 * @return The user which is looked for
	 */
	public Optional<User> getUserById(String id);
	
	/**
	 * To get an specified product by id
	 * @return The product which is looked for
	 */
	public Optional<Product> getProductById(String id);
	
	/**
	 * To get an specified product type by id
	 * @return The product type which is looked for
	 */
	public Optional<ProductType> getProductTypeById(String id);
	
	/**
	 * To get an specified order by id
	 * @return The order which is looked for
	 */
	public Optional<Order> getOrderById(String id);

	/**
	 * Delete an user with an id
	 * @param id The id of the user to delete.
	 */
	void deleteUserbyId(String id);
	
}
