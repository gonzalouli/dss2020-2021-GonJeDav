package com.jedago.practica_dss.core;

import java.util.List;

interface IOrder {
	public long getId_oder();
	public double getPrice();
	public List<IOrderLine> getProducts();
	public void setProducts(Product currenProduct );
	public void setNProducts(Product currenProduct , int cant );
	public void showProducts();
	public void addProductToOrder();
	public void addProductToOrder(Product currentProduct , int cant);
	public void deleteProductFromOrder(Product currentProduct);

	
	
	
}
