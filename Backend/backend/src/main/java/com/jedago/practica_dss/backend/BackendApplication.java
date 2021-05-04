package com.jedago.practica_dss.backend;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jedago.practica_dss.core.Cafe;
import com.jedago.practica_dss.core.CashBox;
import com.jedago.practica_dss.core.Order;
import com.jedago.practica_dss.core.OrderLine;
import com.jedago.practica_dss.core.Product;
import com.jedago.practica_dss.core.ProductType;
import com.jedago.practica_dss.core.User;
import com.jedago.practica_dss.core.exceptions.NoStockException;
import com.jedago.practica_dss.persistance.OrdersRepositoryByFile;
import com.jedago.practica_dss.persistance.ProductsRepositoryByFile;
import com.jedago.practica_dss.persistance.UsersRepositoryByFile;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @version 1.0 12/4/21
 * Endpoints of the backend application
 * @author Gonzalo Ulibarri Garcia
 *
 */
@SpringBootApplication
@RestController
public class BackendApplication {
	
	private static OrdersRepositoryByFile or;
	private static ProductsRepositoryByFile pr;
	private static UsersRepositoryByFile ur;
	private static Cafe cafe;
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
	
	public static void main(String[] args) throws Exception {
		
		or = new OrdersRepositoryByFile();
		pr = new ProductsRepositoryByFile();
		ur = new UsersRepositoryByFile();
		
		cafe =new Cafe(or, pr, ur);
		
		SpringApplication.run(BackendApplication.class, args);
	}
	
	/**
	 * Returns a set of available product.
	 * @return List with the available products.
	 */
	@GetMapping("/products")
	public List<Product> getProducts() throws Exception 
	{
		return cafe.getAvailableProducts(); 
	}
	
	/**
	 * Returns a set of all orders.
	 * @return List with all orders.
	 */
	@JsonIgnore
	@GetMapping("/orders")
	public List<Order> getAllOrders() throws Exception 
	{
		return cafe.getRegisteredOrders(); 
	}
	
	
	/**
	 * Returns a set of available product types.
	 * @return List with the available products types.
	 */
	@GetMapping("/productTypes")
	public List<ProductType> getProductTypes() throws Exception 
	{
		return cafe.getAvailableProductTypes(); 
	}
	
	/**
	 * Returns a set of available product.
	 * @param id The id of the product type to filter the products.
	 * @return List with the available products to a determined type.
	 */	
	@JsonIgnore
	@GetMapping("/products/type")
	public List<Product> getProductsByType(@RequestParam String id) throws Exception 
	{
		return cafe.getAvailableProductsbyType(id); 
	}

	/**
	 * Returns a set of all users.
	 * @return List of all users.
	 */	
	@JsonIgnore
	@GetMapping("/users")
	public List<User> getUsers() throws Exception 
	{
		return cafe.getRegisteredUsers(); 
	}
	
	/**
	 * Returns a set of orders id's to a determined user.
	 * @param iduser The id of the user to give all his orders.
	 * @return List of orders id's that own to the given user.
	 */	
	@GetMapping("/users/orders/{iduser}")
	public List<String> getUserOrders(@PathVariable String iduser ) throws Exception 
	{
		Optional<User> u = cafe.getUserById(iduser);
		List<Order> orders = new ArrayList<Order>();
		if(u.isPresent()) 
		{
			orders = cafe.getUserOrders(u.get());
		}
		List<String> idOrders = new ArrayList<String>();
		for(Order o: orders) {
			idOrders.add(o.getId_order());
		}
		
		return idOrders;  
	}
	
	/**
	 * Returns the id to the user added.
	 * @param newUser The user added to the system.
	 * @return A string to represent the user id.
	 */	
	@PostMapping("/user")
	public String newUser(@RequestBody User newUser) throws Exception 
	{
		cafe.registerUser(newUser);
		return newUser.getIdUser();
	}
	
	/**
	 * Set a pickup time to a order given.
	 * @param idorder The id of the order to update the pick up time
	 * @param ldt The date to pick up.
	 */	
	@PostMapping("/order/time")
	public void setPickUpTime( @RequestParam String idorder, @RequestParam LocalDateTime ldt) throws Exception 
	{
		Order order = null;
		if(cafe.getOrderById(idorder).isPresent())
		{
			order = cafe.getOrderById(idorder).get();
			cafe.setPickUpTime(order, ldt);
		}
	}
	
	/**
	 * Get an order from a id given..
	 * @param idorder The id of the order to show.
	 * @return The order with the id given.
	 */	
	@GetMapping("/order/{id_order}")
	public Order getOrderWithID(@PathVariable String id_order) {
		Order noOrder = null;
		if(cafe.getOrderById(id_order).isPresent())
			return cafe.getOrderById(id_order).get();
		return noOrder;
		
	}
	
	/**
	 * Create a new order binded to an existing user.
	 * @param idUser The id of the user to add a new order.
	 * @return The id of the order created.
	 */	
	@PostMapping("/order/user") //primero create user, y le pasamos el id user aqui
	public String createOrder(@RequestParam String idUser) 
	{
		return cafe.newOrder(idUser).getId_order();
	}
	
	/**
	 * Add a product to an existing order
	 * @param idproduct The id of the product to add.
	 * @param cant The quantity to product to add.
	 * @param idorder The id of order to add the product.
	 */	
	@PostMapping("/order/product")
	public void addProduct(@RequestParam(required=true) String idproduct, @RequestParam int cant, @RequestParam(required=true) String idorder ) throws NoStockException 
	{
		if(cafe.getProductById(idproduct).isPresent() &&  cafe.getOrderById(idorder).isPresent() ) {
			Product newProduct = cafe.getProductById(idproduct).get();
			cafe.addProductToOrder(cafe.getOrderById(idorder).get(), newProduct, cant);

		}
	}
	
	/**
	 * Delete a determined quantity of a product from an order.
	 * @param idproduct The id of the product to remove a determined quantity.
	 * @param cant The quantity to product to remove.
	 * @param idorder The id of order to remove a quantity of the product.
	 */	
	@DeleteMapping("/order/delete")
	public void deleteProduct(@RequestParam(required=true) String idproduct, @RequestParam(required=true) int cant,
			@RequestParam(required=true, name="idorder") String idorder ) 
	{
		if(cafe.getProductById(idproduct).isPresent() && cafe.getOrderById(idorder).isPresent() ) 
		{	
			Product oldProduct = cafe.getProductById(idproduct).get();
			Order order = cafe.getOrderById(idorder).get();
			cafe.deleteProductFromOrder(order , oldProduct, cant );
		}
	}
	
	/**
	 * Get a day's cash box.
	 * @param ld The date to return the cashbox.
	 * @return The cashbox to a day given.
	 */	
	@GetMapping("/cashbox")
	public CashBox getCashBox(@RequestParam LocalDate ld) {
		
		if(ld==null)
			return cafe.getTodayCashBox();
		else
			return cafe.getCashBox(ld);
	}

	/**
	 * End a determined order.
	 * @param idorder The od to the order to finish.
	 */	
	@PostMapping("/order/end")
	public void finishOrder(@RequestParam(required=true) String idorder) throws Exception 
	{
		EnvioEmail mail = new EnvioEmail();
		if(  cafe.getOrderById(idorder).isPresent() ) 
		{
			Order order =  cafe.getOrderById(idorder).get();
			mail.sendEmail(order);
			cafe.FinishOrder(order);
		}
	}
	
	/**
	 * Updater the first name of an existing user.
	 * @param id_user The id to the user to modified.
	 * @param firstname The parameter to modified in the user given.
	 */	
	@PatchMapping("/user/firstname")
	public void updateUserFirstName(@RequestParam(required=true) String id_user, @RequestParam(required=true) String firstname) throws Exception 
	{
		User user = cafe.getUserById(id_user).get();
		cafe.updateUserFirstName(user, firstname);

	}
	
	/**
	 * Updater the last name of an existing user.
	 * @param id_user The id to the user to modified.
	 * @param firstname The parameter to modified in the user given.
	 */	
	@PatchMapping("/user/lastname")
	public void updateUserLastName(@RequestParam(required=true) String id_user, @RequestParam(required=true) String lastname) throws Exception 
	{
		User user = cafe.getUserById(id_user).get();
		cafe.updateUserLastName(user, lastname);
	}
	
	/**
	 * Updater the dni of an existing user.
	 * @param id_user The id to the user to modified.
	 * @param firstname The parameter to modified in the user given.
	 */	
	@PatchMapping("/user/dni")
	public void updateUserDni(@RequestParam(required=true) String id_user, @RequestParam(required=true) String dni) throws Exception 
	{
		User user = cafe.getUserById(id_user).get();
		cafe.updateUserDNI(user, dni);

	}
	
	/**
	 * Updater the birthdate of an existing user.
	 * @param id_user The id to the user to modified.
	 * @param firstname The parameter to modified in the user given.
	 */	
	@PatchMapping("/user/birthdate")
	public void updateUserBirthdate(@RequestParam(required=true) String id_user, @RequestParam(required=true) String birthdate) throws Exception 
	{	
		LocalDate localDate1 = LocalDate.parse(birthdate);
		User user = cafe.getUserById(id_user).get();
		cafe.updateUserBirthDate(user, localDate1);
	}
	
	/**
	 * Delete an existing user by an id.
	 * @param id_user The id to the user to delete.
	 */	
	@DeleteMapping("/user/delete")
	public void deleteUser(@RequestParam(required=true) String id_user) throws Exception 
	{
		cafe.deleteUserbyId(id_user);
	}
	
	
	
}
