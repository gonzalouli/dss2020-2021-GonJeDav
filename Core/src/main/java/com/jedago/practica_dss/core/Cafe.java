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
		return o;
	}
	
	/**
	 * Create a new order bind to an user
	 * @param u the user to bind the new order
	 * @return the new order created
	 */
	public Order newOrder(User u)
	{
		Order o = new Order();
		o.setUser(u);
		u.setOrder(o);
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
	
	@Override
	/**
	 * Returns a set of available products
	 * @return List with the availables products
	 */
	public List<Product> getAvailableProducts() {
		
		List<Product> availableProducts = new ArrayList<Product>();
		
		try {
			availableProducts = this.productsRepository.findAllStockAvailable();
		} catch (Exception e) {e.printStackTrace();}
		
		return availableProducts;
	}
	
	@Override
	public List<ProductType> getAvailableProductTypes() {
		List<ProductType> productTypeList = new ArrayList<ProductType>();
		
		try {
			productTypeList = this.productsRepository.findAllTypes();
		} catch (Exception e) {e.printStackTrace();}
		
		return productTypeList;
	}
	
	@Override
	public List<Product> getAvailableProductsbyType(ProductType t) {
		List<Product> seekProducts = new ArrayList<Product>();
		  
		try {
			seekProducts = this.productsRepository.findAllStockAvailableByType(t);
		} catch (Exception e) {e.printStackTrace();}
		return seekProducts;
	}
	
	@Override
	public List<Product> getAvailableProductsbyType(int id) {
		List<Product> productList = new ArrayList<Product>();
		Optional<ProductType> seekProductType = Optional.empty();
		
		try {
			seekProductType = this.productsRepository.findTypeById(id);
		} catch (Exception e1) {e1.printStackTrace();}
		
		if(!seekProductType.isEmpty())
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
			ord.addProductToOrder(p, c);
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
		ord.deleteProductFromOrder(p, 1);
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
				if(registeredProduct.getID() == orderProduct.getID())
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
		this.ordersRepository.add(ord);
		this.productsRepository.save(currentProducts);
	}

	@Override
	/**
	 * Returs the amount of registered orders and the money earned in a day
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
				//Sumamos un pedido
				cb.incrementOrders();
				//Añadimos el total del pedido 
				cb.addtoTotal(o.getPrice());
			}
		}
		
		return cb;
	}

	@Override
	public CashBox getTodayCashBox() {
		LocalDate today = LocalDate.now();
		CashBox todayCashBox = new CashBox(); 
		try {
			todayCashBox = this.getCashBox(today);
		} catch (Exception e) {e.printStackTrace();}
		return todayCashBox;
	}

	@Override
	public void registerUser(User u) throws Exception {
		this.usersRepository.add(u);
	}

	@Override
	public void updateUserFirstName(User u, String newFirstName) throws Exception {
		int id = (int) u.getIdUser();
		u.setFirstName(newFirstName);
		this.usersRepository.update(id, u);
	}

	@Override
	public void updateUserLastName(User u, String newLastName) throws Exception{
		int id = (int) u.getIdUser();
		u.setLastName(newLastName);
		this.usersRepository.update(id, u);
	}

	@Override
	public void updateUserBirthDate(User u, LocalDate newBirthDate) throws Exception{
		int id = (int) u.getIdUser();
		u.setBirthDate(newBirthDate);
		this.usersRepository.update(id, u);
	}

	@Override
	public void updateUserDNI(User u, String newDNI) throws Exception{
		int id = (int) u.getIdUser();
		u.setDni(newDNI);
		this.usersRepository.update(id, u);
	}

	@Override
	public List<Order> getUserOrders(User u) {
		List<Order> orderList = new ArrayList<Order>();
		orderList = u.getOrders();
		return orderList;
	}

	@Override
	public List<User> getRegisteredUsers() {
		List<User> users = new ArrayList<User>();
		try {
			users = this.usersRepository.findAll();
		} catch (Exception e) {e.printStackTrace();}
		return users;
	}

}
