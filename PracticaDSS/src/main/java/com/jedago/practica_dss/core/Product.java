package com.jedago.practica_dss.core;

class Product implements IProduct {
	private
	
	public String getID() {
		
	}
	public String getName() {
		return name;
	}
	public double  getPriceUnit() {
			return priceunit;
	}
	public int 	  getStock() {
		return stock;
	}
	public double  setPriceUnit(double price_) {
		this.price = price_;
	}
	public int 	  setStock(int stock_) {
		this.stock = stock_;
	}
}
