package com.jedago.practica_dss.core;

public interface IProduct {
	
	public int 	  	getStock();
	public void	  	setStock(int stock_); 
	public void  	setPriceUnit(double price_);
	public double  	getPriceUnit();
	public String getName();
	public int getID();
}
