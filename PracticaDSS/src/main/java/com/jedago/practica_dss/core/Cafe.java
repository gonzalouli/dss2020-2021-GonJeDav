/**
 * 
 */
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
		super();
		this.orders_ = orders_;
		this.products_ = products_;
	}

	@Override
	public void newOrder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showProducts() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProductToOrder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProductFromOrder() {
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
