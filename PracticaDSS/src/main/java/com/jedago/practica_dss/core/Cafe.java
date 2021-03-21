package com.jedago.practica_dss.core;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class Cafe implements ICafe {

	private List<Order> orders_;
	private List<Product> products_;
	private CashBox cb;
	
	
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
	public Order newOrder() {
		Order o = new Order();
		return o;
	}

	@Override
	public List<Product> getAvailableProducts() {
		return products_;
	}

	@Override
	public void addProductToOrder(Order ord, Product p) {
		ord.addProductToOrder(p);
	}
	
	@Override
	public void addProductToOrder(Order ord, Product p, int c) {
		ord.addProductToOrder(p, c);
	}

	@Override
	public void deleteProductFromOrder(Order ord, Product p) {
		ord.deleteProductFromOrder(p, 1);
	}
	
	@Override
	public void deleteProductFromOrder(Order ord, Product p, int c) {
		ord.deleteProductFromOrder(p, c);
	}

	@Override
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
	public CashBox getCashBox() {
		return(cb);
	}


}
