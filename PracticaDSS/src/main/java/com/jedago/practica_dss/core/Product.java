package com.jedago.practica_dss.core;

public class Product{
	private String	name;
	private int		id;
	private int 	stock;
	private double 	priceunit;
	private static	int i=1;
	
	
	public Product(String name, int stock, double price) {
		super();
		this.name = name;
		this.stock = stock;
		this.priceunit = price;
		this.id = i;
		i++;
	}
	public int getID() {
		return id;
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
	public void setPriceUnit(double price_) {
		this.priceunit = price_;
	}
	public void  setStock(int stock_) {
		this.stock = stock_;
	}
}
