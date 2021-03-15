package com.jedago.practica_dss.core;

import java.util.List;

/**
 * @author jeseg
 *
 */
class Cafe implements ICafe {

	//Atributos?
	private List<IOrder> orders_;
	private List<IProduct> products_;
	
	public Cafe(List<IOrder> orders_, List<IProduct> products_) {
		this.orders_ = orders_;
		this.products_ = products_;
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
		
	}
	
	@Override
	public void deleteProductFromOrder(IOrder ord, IProduct p, int c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void FinishOrder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showCashBox() {
		// TODO Auto-generated method stub
		//Recorrer todas las ordenes y sumar su precio y calcular cuantas órdenes hay en total
		
	}


}
