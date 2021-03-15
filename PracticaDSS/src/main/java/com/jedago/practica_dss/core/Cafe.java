package com.jedago.practica_dss.core;

import java.util.Iterator;
import java.util.List;

class Cafe implements ICafe {

	private List<IOrder> orders_;
	private List<IProduct> products_;
	private int nOrders;
	private float total;
	
	
	public Cafe(List<IOrder> orders_, List<IProduct> products_) {
		this.orders_ = orders_;
		this.products_ = products_;
		this.nOrders = orders_.size();
		this.total=0;
		for(IOrder ord:orders_)
		{
			this.total+=ord.getPrice();
		}
	}

	@Override
	public IOrder newOrder() {
		IOrder o = new Order();
		return o;
	}

	@Override
	public void showProducts() {
		int i = 1;
		for(IProduct p : products_)
		{
			System.out.println(i+". "+p.getname()+" ("+p.getprice()+")");
			i++;
		}
	}

	@Override
	public void addProductToOrder(IOrder ord, IProduct p) {
		ord.addProductToOrder(p);
		
	}
	
	@Override
	public void addProductToOrder(IOrder ord, IProduct p, int c) {
		ord.addProductToOrder(p, c);
	}

	@Override
	public void deleteProductFromOrder(IOrder ord, IProduct p) {
		ord.deteteProductFromOrder(p, 1);
	}
	
	@Override
	public void deleteProductFromOrder(IOrder ord, IProduct p, int c) {
		ord.deteteProductFromOrder(p, c);
	}

	@Override
	public void FinishOrder(IOrder ord) {
		IProduct p;
		int c;
		boolean found;
		for(IOrderLine ol: ord) //Recorro los orderLine de cada order
		{
			//Pillar de cada orderLine el producto y las cantidadesy
			p = ol.getProduct();
			c = ol.getAmount();
			Iterator<IProduct> i = products_.iterator();
			found=false;
			while(!found && i.hasNext())
			{
				//Actualizarlos de la lista de productos disponibles
				if(i.getID() == p.getID())
				{
					//Restarle la cantidad al stock
					i.stock()-=c;
					//Eliminarlo si se queda a 0
					if(i.stock() == 0)
						i.remove();
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
