package com.jedago.practica_dss.core;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jesús Serrano Gallán
 *	@version 1.0
 */
public class Cafe implements ICafe {

	private List<Order> orders_;
	private List<Product> products_;
	private CashBox cb;
	
	
	/**
	 * Constructor
	 * @param orders_ List of initial orders (regularly a void one)
	 * @param products_ List of initial available products
	 */
	public Cafe(List<Order> orders_, List<Product> products_) {
		this.orders_ = orders_;
		this.products_ = products_;
		this.cb = new CashBox();
		this.cb.setnOrders(orders_.size());
		this.cb.setTotal(BigDecimal.ZERO);
		for(Order ord: orders_)
		{
			this.cb.addtoTotal(ord.getPrice());
		}
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

	@Override
	/**
	 * Returns a set of available products
	 * @return List with the availables products
	 */
	public List<Product> getAvailableProducts() {
		return products_;
	}

	@Override
	/**
	 * To add a unit of a product to an order
	 * @param ord the order in which you want to add the product
	 * @param p the product which you want to be added
	 */
	public void addProductToOrder(Order ord, Product p) {
		ord.addProductToOrder(p);
	}
	
	@Override
	/**
	 * To add several units of a product to an order
	 * @param ord the order in which you want to add the product
	 * @param p the product which you want to be added
	 * @param c the quantity of the product
	 */
	public void addProductToOrder(Order ord, Product p, int c) {
		ord.addProductToOrder(p, c);
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
	public void FinishOrder(Order ord) {
		Product p;
		int c;
		boolean found;
		for(OrderLine ol: ord) //Recorro los orderLine de cada order
		{
			//Pillar de cada orderLine el producto y las cantidades
			p = ol.getProduct();
			c = ol.getAmount();
			Iterator<Product> it = products_.iterator();
			Product ip;
			found=false;
			while(!found && it.hasNext())
			{
				ip = it.next();
				//Actualizarlos de la lista de productos disponibles
				if(( ip.getID() == p.getID()))
				{
					//Restarle la cantidad al stock
					ip.setStock(ip.getStock() - c); 
					//Eliminarlo si se queda a 0
					if((ip.getStock() == 0))
						it.remove();
					found=true;
				}
			}
		}
		this.cb.addtoTotal(ord.getPrice());
		this.cb.incrementOrders();
		orders_.add(ord);
	}

	@Override
	/**
	 * Returs the amount of registered orders and the money earned
	 * @return a CashBox with the amount of orders registered and the money earned
	 */
	public CashBox getCashBox() {
		return(cb);
	}


}
