package com.jedago.practica_dss.core;

import java.util.Date;
import java.util.List;

interface IOrder {
	public long getId_oder();
	public double getPrice();
	public Date getDate();
	public List<IOrderLine> getProducts();
	public void setProduct(IOrderLine currentOrderLine ); 
	public void setNProducts(IOrderLine currentOrderLine ); 
	public void showProducts();
	public void addProductToOrder(IProduct currentProduct);
	public void addProductToOrder(IProduct currentProduct , int cant);
	public void deteteProductFromOrder(IOrderLine currentOrderLine, int cant); 
	public void deleteOrderlineFromOrder(IOrderLine currentOrderLine);

}
