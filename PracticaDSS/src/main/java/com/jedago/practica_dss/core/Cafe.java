package com.jedago.practica_dss.core;

import java.util.Iterator;
import java.util.List;

public class Cafe implements ICafe {

	private List<Order> orders_;
	private List<Product> products_;
	private int nOrders;
	private float total;
	
	
	public Cafe(List<Order> orders_, List<Product> products_) {
		this.orders_ = orders_;
		this.products_ = products_;
		this.nOrders = orders_.size();
		this.total=0;
		for(Order ord: orders_)
		{
			this.total+=ord.getPrice();
		}
	}

	@Override
	public Order newOrder() {
		Order o = new Order();
		return o;
	}

	@Override
	public void showProducts() {
		for(Product p : products_)
		{
			System.out.println(p.getID() +". "+p.getName()+" ("+p.getPriceUnit()+")");
		}
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
		ord.deteteProductFromOrder(p, 1);
	}
	
	@Override
	public void deleteProductFromOrder(Order ord, Product p, int c) {
		ord.deteteProductFromOrder(p, c);
	}

	@Override
	public void FinishOrder(Order ord) {
		Product p;
		int c;
		boolean found;
		for(OrderLine ol: ord) //Recorro los orderLine de cada order
		{
			//Pillar de cada orderLine el producto y las cantidadesy
			p = ol.getProduct();
			c = ol.getAmount();
			Iterator<Product> i = products_.iterator();
			found=false;
			while(!found && i.hasNext())
			{
				//Actualizarlos de la lista de productos disponibles
				if(( ((Product) i).getID() == p.getID()))
				{
					//Restarle la cantidad al stock
					((Product) i).setStock(((Product) i).getStock() - c); 
					//Eliminarlo si se queda a 0
					if(((Product) i).getStock() == 0)
						i.remove();
					found=true;
				}
				i.next();
			}
		}
		total+=ord.getPrice();
		nOrders++;
		orders_.add(ord);
	}

	@Override
	public void showCashBox() {
		System.out.println("Pedidos registrados: "+nOrders);
		System.out.println("Caja: "+total);
	}


}
