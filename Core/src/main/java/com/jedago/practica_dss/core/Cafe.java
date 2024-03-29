package com.jedago.practica_dss.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.jedago.practica_dss.core.exceptions.NoStockException;
import com.jedago.practica_dss.persistance.OrdersRepository;
import com.jedago.practica_dss.persistance.ProductsRepository;
import com.jedago.practica_dss.persistance.UsersRepository;

/**
 * @author Jesús Serrano Gallán
 *	@version 2.0
 */
public class Cafe implements ICafe {
	private OrdersRepository ordersRepository;
	private ProductsRepository productsRepository;
	private UsersRepository usersRepository;
	
	/**
	 * Constructor with orders and products repository
	 * @param orders Repository with the orders in the system
	 * @param products Repository with the products in the system
	 * @throws Exception

	 */
	public Cafe(OrdersRepository orders, ProductsRepository products) throws Exception { 
		this.ordersRepository = orders;
		this.productsRepository = products;
	}
	
	/**
	 * Constructor with orders, products and users repository
	 * @param orders Repository with the orders in the system
	 * @param products Repository with the products in the system
	 * @param users Repository with the users in the system
	 * @throws Exception
	 */
	public Cafe(OrdersRepository orders, ProductsRepository products, UsersRepository users) throws Exception { 
		this.ordersRepository = orders;
		this.productsRepository = products;
		this.usersRepository = users;
	}
	
	@Override
	/**
	 * Create a new order
	 * @return the new ordenr created
	 */
	public Order newOrder() {
		Order o = new Order();
		try {
			this.ordersRepository.add(o);
		} catch (Exception e) {e.printStackTrace();}
		return o;
	}
	
	@Override
	public Order newOrder(User u)
	{
		Order o = new Order();
		o.setUser(u);
		u.addOrder(o);
		try {
			this.usersRepository.update(u.getIdUser(), u);
			this.ordersRepository.add(o);
		} catch (Exception e) {e.printStackTrace();}
		return o;
	}
	
	@Override
	public Optional<Order> newOrder(String uid)
	{
		Optional<User> user = Optional.empty();
		Optional<Order> o = Optional.empty();
		try {
			user = this.usersRepository.findById(uid);
		} catch (Exception e) {e.printStackTrace();}
		if(user.isPresent())
		{
			User found = user.get();
			o = Optional.of(this.newOrder(found)); 
		}
		return o;
	}
	
	/**
	 * Set the desired pickup time for the order
	 * @param o Order to set the collection time
	 * @param t Time to pick up the order
	 */
	public void setPickUpTime(Order o, LocalDateTime t)
	{
		o.setPickUpTime(t);
		try {
			this.ordersRepository.update(o.getId_order(), o);
		} catch (Exception e) {e.printStackTrace();}
	}

	@Override
	/**
	 * Returns a set of registered orders
	 * @return List with the registered orders
	 */
	public List<Order> getRegisteredOrders(){
		List<Order> orders = new ArrayList<Order>();
		try {
			orders = this.ordersRepository.findAll();
		} catch (Exception e) {e.printStackTrace();}
		return orders;
	}
	

	/**
	 * Returns a set of available products
	 * @return List with the available products
	 */
	@Override
	public List<Product> getAvailableProducts() {
		
		List<Product> availableProducts = new ArrayList<Product>();
		
		try {
			availableProducts = this.productsRepository.findAllStockAvailable();
		} catch (Exception e) {e.printStackTrace();}
		
		return availableProducts;
	}
	
	/**
	 * Returns a set of available product types.
	 * @return List with the available products types
	 */
	@Override
	public List<ProductType> getAvailableProductTypes() {
		List<ProductType> productTypeList = new ArrayList<ProductType>();

		try {
			productTypeList = this.productsRepository.findAllTypes();

		} catch (Exception e) {e.printStackTrace();}
		
		return productTypeList;
	}
	
	/**
	 * Returns a set of available product.
	 * @param t Product type to filter the products to return.
	 * @return List with the available products.
	 */
	@Override
	public List<Product> getAvailableProductsbyType(ProductType t) {
		List<Product> seekProducts = new ArrayList<Product>();
		  
		try {
			seekProducts = this.productsRepository.findAllStockAvailableByType(t);
		} catch (Exception e) {e.printStackTrace();}
		return seekProducts;
	}
	
	/**
	 * Returns a set of available product.
	 * @param id ID to the product type to filter the products to return.
	 * @return List with the available products.
	 */
	@Override
	public List<Product> getAvailableProductsbyType(String id) {
		List<Product> productList = new ArrayList<Product>();
		Optional<ProductType> seekProductType = Optional.empty();
		
		try {
			seekProductType = this.productsRepository.findTypeById(id);
		} catch (Exception e1) {e1.printStackTrace();}
		
		if(seekProductType.isPresent())
			try {
				productList = this.productsRepository.findAllStockAvailableByType(seekProductType.get());
			} catch (Exception e) {e.printStackTrace();}
	
		return productList;
	}

	@Override
	/**
	 * To add a unit of a product to an order
	 * @param ord the order in which you want to add the product
	 * @param p the product which you want to be added
	 */
	public void addProductToOrder(Order ord, Product p) throws NoStockException{
		addProductToOrder(ord, p, 1);
	}	
	
	@Override
	/**
	 * To add several units of a product to an order
	 * @param ord the order in which you want to add the product
	 * @param p the product which you want to be added
	 * @param c the quantity of the product
	 */
	public void addProductToOrder(Order ord, Product p, int c) throws NoStockException{
		if(p.getStock() >= c || c < 1)
		{
			ord.addProductToOrder(p, c);
			try {
				this.ordersRepository.update(ord.getId_order(), ord);
			} catch (Exception e) {e.printStackTrace();}
		}
		else
			throw new NoStockException("No hay suficiente stock del producto " + p.getID());
	}

	@Override
	/**
	 * To delete a unit of a product from an order
	 * @param ord the order in which you want to add the product
	 * @param p the product which you want to be added
	 */ 
	public void deleteProductFromOrder(Order ord, Product p) {
		this.deleteProductFromOrder(ord, p, 1);
	}
	
	@Override
	/**
	 * To delete several unit of a product from an order
	 * @param ord the order in which you want to add the product
	 * @param p the product which you want to be added
	 * @param c the quantity of the product you wannt to delete 
	 */
	public void deleteProductFromOrder(Order ord, Product p, int c) {
		ord.deleteProductFromOrder(p, c);
		try {
			this.ordersRepository.update(ord.getId_order(), ord);
		} catch (Exception e) {e.printStackTrace();}
	}

	@Override
	/**
	 * Register the order to finish it
	 * @param ord the order you want to register
	 */
	public void FinishOrder(Order ord) throws Exception{
		Product orderProduct;
		int orderQuantity;
		boolean found;
		List<Product> currentProducts = this.productsRepository.findAll();
		for(OrderLine ol: ord) //Recorro los orderLine de cada order
		{
			//Pillar de cada orderLine el producto y las cantidades
			orderProduct = ol.getProduct();
			orderQuantity = ol.getAmount();

			//Iteramos a través de la lista de productos disponibles
			Iterator<Product> it = currentProducts.iterator();
			Product registeredProduct;
			found=false;
			
			while(!found && it.hasNext())
			{
				registeredProduct = it.next();
				//Actualizarlos de la lista de productos disponibles
				if(registeredProduct.getID().equals(orderProduct.getID()))
				{
					if(registeredProduct.getStock() >= orderQuantity)
						//Restarle la cantidad al stock
						registeredProduct.setStock(registeredProduct.getStock() - orderQuantity); 
					else
						throw new NoStockException("No hay suficiente stock del producto " + orderProduct.getID());
					
					found=true;
				}
			}
		}
		ord.setFinished(true);
		this.ordersRepository.update(ord.getId_order(), ord);
		this.productsRepository.save(currentProducts);

	}

	@Override
	/**
	 * Returns the amount of registered orders and the money earned in a day
	 * @return a CashBox with the amount of orders registered and the money earned
	 * @param date The date of the CashBox you want to check
	 */
	public CashBox getCashBox(LocalDate date)
	{
		CashBox cb = new CashBox();
		List<Order> orders = new ArrayList<Order>();
		try {
			orders = this.ordersRepository.findAll();
		} catch (Exception e) {e.printStackTrace();}

		//Recorrer la lista de pedidos y meter en el CashBox los pedidos con la fecha deseada
		for(Order o:orders)
		{
			//Miramos si la fecha corresponde con la buscada
			if(o.getDate().isEqual(date))
			{
				if(o.isFinished())
				{
					//Sumamos un pedido
					cb.incrementOrders();
					//Añadimos el total del pedido 
					cb.addtoTotal(o.getPrice());
				}
			}
		}
		
		return cb;
	}

	/**
	 * Returns the amount of registered orders and the money earned today
	 * @return a CashBox with the amount of orders registered and the money earned
	 */
	@Override
	public CashBox getTodayCashBox() {
		LocalDate today = LocalDate.now();
		CashBox todayCashBox = new CashBox(); 
		try {
			todayCashBox = this.getCashBox(today);
		} catch (Exception e) {e.printStackTrace();}
		return todayCashBox;
	}
	/**
	 * Returns the id of the current register User
	 * @return a String that represent the user
	 * @param u User to add to the repository.
	 */
	@Override
	public String registerUser(User u) throws Exception {
		this.usersRepository.add(u);
		return u.getIdUser();
	}

	/**
	 * Update a  parameter of the user given.
	 * @param u User to update
	 * @param newFirstName New parameter to update in user u.
	 */
	@Override
	public void updateUserFirstName(User u, String newFirstName) throws Exception {
		String id = u.getIdUser();
		u.setFirstName(newFirstName);
		this.usersRepository.update(id, u);
	}

	/**
	 * Update a  parameter of the user given.
	 * @param u User to update
	 * @param newLastName New parameter to update in user u.
	 */
	@Override
	public void updateUserLastName(User u, String newLastName) throws Exception{
		String id =  u.getIdUser();
		u.setLastName(newLastName);
		this.usersRepository.update(id, u);
	}

	/**
	 * Update a  parameter of the user given.
	 * @param u User to update
	 * @param newBirthDate New parameter to update in user u.
	 */
	@Override
	public void updateUserBirthDate(User u, LocalDate newBirthDate) throws Exception{
		String id =  u.getIdUser();
		u.setBirthDate(newBirthDate);
		this.usersRepository.update(id, u);
	}

	/**
	 * Update a  parameter of the user given.
	 * @param u User to update
	 * @param newBirthDate New parameter to update in user u.
	 */
	@Override
	public void updateUserDNI(User u, String newDNI) throws Exception{
		String id =  u.getIdUser();
		u.setDni(newDNI);
		this.usersRepository.update(id, u);
	}

	/**
	 * Get the orders with a user given.
	 * @return A list of orders that own to user u
	 * @param u The user to own all orders.
	 */
	@Override
	public List<Order> getUserOrders(User u) {
		List<Order> orderList = new ArrayList<Order>();
		orderList = u.getOrders();
		return orderList;
	}

	/**
	 * Get all the users of the system.
	 * @return A list of users registered.
	 */
	@Override
	public List<User> getRegisteredUsers() {
		List<User> users = new ArrayList<User>();
		try {
			users = this.usersRepository.findAll();
		} catch (Exception e) {e.printStackTrace();}
		return users;
	}

	/**
	 * Get an optional of an user by an id.
	 * @return A optional user.
	 * @param id The id of the user to return.
	 */
	@Override
	public Optional<User> getUserById(String id) {
		Optional<User> u = Optional.empty();
		try {
			u = this.usersRepository.findById(id);
		} catch (Exception e) {e.printStackTrace();}
		return u;
	}

	/**
	 * Get an optional of a product by an id.
	 * @return A optional product.
	 * @param id The id of the product to return.
	 */
	@Override
	public Optional<Product> getProductById(String id) {
		Optional<Product> p = Optional.empty();
		try {
			p = this.productsRepository.findById(id);
		} catch (Exception e) {e.printStackTrace();}
		return p;
	}

	/**
	 * Get an optional of a product type by an id.
	 * @return A optional product type.
	 * @param id The id of the product type to return.
	 */
	@Override
	public Optional<ProductType> getProductTypeById(String id) {
		Optional<ProductType> pt = Optional.empty();
		try {
			pt = this.productsRepository.findTypeById(id);
		} catch (Exception e) {e.printStackTrace();}
		return pt;
	}

	/**
	 * Get an optional of a product type by an id.
	 * @return A optional product type.
	 * @param id The id of the product type to return.
	 */
	@Override
	public Optional<Order> getOrderById(String id) {
		Optional<Order> o = Optional.empty();
		try {
			o = this.ordersRepository.findById(id);
		} catch (Exception e) {e.printStackTrace();}
		return o;
	}
	
	/**
	 * Delete an user by an id.
	 * @param id The id of the user to delete.
	 */
	@Override
	public void deleteUserbyId(String id)
	{ 
		Optional<User> u = this.getUserById(id);
		if(u.isPresent())
			try {
				this.usersRepository.delete(u.get());
			} catch (Exception e) {e.printStackTrace();}
	}
	

}
