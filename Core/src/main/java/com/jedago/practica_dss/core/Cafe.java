package com.jedago.practica_dss.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jedago.practica_dss.core.exceptions.NoStockException;
import com.jedago.practica_dss.persistance.OrdersRepository;
import com.jedago.practica_dss.persistance.ProductsRepository;
import com.jedago.practica_dss.persistance.UsersRepository;

/**
 * @author Jesús Serrano Gallán
 *	@version 1.0
 */
public class Cafe implements ICafe {

	private List<Order> orders;
	private List<Product> products;
	private List<User> users;
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
		this.orders = orders.readOrders();
		this.productsRepository = products;
		this.products = products.readProducts();
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
		this.orders = orders.readOrders();
		this.productsRepository = products;
		this.products = products.readProducts();
		this.usersRepository = users;
		this.users = users.readUsers();
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
	public List<Order> getRegisteredOrders() {
		return orders;
	}
	
	@Override
	/**
	 * Returns a set of available products
	 * @return List with the availables products
	 */
	public List<Product> getAvailableProducts() {
		
		List<Product> availableProduct = new ArrayList<Product>();
		
		for(Product p: products)
		{
			if(p.getStock()>0)
			{
				availableProduct.add(p);
			}
		}
		
		return availableProduct;
	}
	
	@Override
	public List<ProductType> getAvailableProductTypes() {
		List<ProductType> productTypeList = new ArrayList<ProductType>();
		
		for(Product p: products)
		{
			if(!productTypeList.contains(p.getType()))
				productTypeList.add(p.getType());
		}
		
		return productTypeList;
	}
	
	@Override
	public List<Product> getAvailableProductsbyType(ProductType t) {
		List<Product> seekProducts = new ArrayList<Product>();
		  
		for(Product p: products)
		{
			if(p.getType().equals(t) && p.getStock()>0)
			{
				seekProducts.add(p);
			}
		}
		
		return seekProducts;
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
		Product p;
		int c;
		boolean found;
		for(OrderLine ol: ord) //Recorro los orderLine de cada order
		{
			//Pillar de cada orderLine el producto y las cantidades
			p = ol.getProduct();
			c = ol.getAmount();
			
			//Iteramos a través de la lista de productos disponibles
			Iterator<Product> it = products.iterator();
			Product ip;
			found=false;
			
			while(!found && it.hasNext())
			{
				ip = it.next();
				//Actualizarlos de la lista de productos disponibles
				if(( ip.getID() == p.getID()))
				{
					if(p.getStock() >= c)
						//Restarle la cantidad al stock
						ip.setStock(ip.getStock() - c); 
					else
						throw new NoStockException("No hay suficiente stock del producto " + p.getID());
					
					found=true;
				}
			}
		}
		orders.add(ord);
		
		//Controlamos serialización
		this.ordersRepository.writeOrders(orders);
		this.productsRepository.writeProducts(products);
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
		return this.getCashBox(today);
	}

	@Override
	public void registerUser(User u) throws Exception {
		this.usersRepository.saveUser(u);
		users = usersRepository.readUsers();
	}

	@Override
	public void updateUserFirstName(User u, String newFirstName) throws Exception {
		int i = users.indexOf(u);
		users.get(i).setFirstName(newFirstName);
		usersRepository.writeUsers(users);
	}

	@Override
	public void updateUserLastName(User u, String newLastName) throws Exception{
		int i = users.indexOf(u);
		users.get(i).setLastName(newLastName);
		usersRepository.writeUsers(users);
	}

	@Override
	public void updateUserBirthDate(User u, LocalDate newBirthDate) throws Exception{
		int i = users.indexOf(u);
		users.get(i).setBirthDate(newBirthDate);
		usersRepository.writeUsers(users);
	}

	@Override
	public void updateUserDNI(User u, String newDNI) throws Exception{
		int i = users.indexOf(u);
		users.get(i).setDni(newDNI);
		usersRepository.writeUsers(users);
	}

}
