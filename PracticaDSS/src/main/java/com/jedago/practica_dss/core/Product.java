package com.jedago.practica_dss.core;

import java.math.BigDecimal;

public class Product{
	private String	name;
	private int		id;
	private int 	stock;
	private BigDecimal 	priceunit;
	public static	int currentId=1;
	
	
	public Product(String name, int stock, BigDecimal d) {
		super();
		this.name = name;
		this.stock = stock;
		this.priceunit = d;
		this.id = currentId;
		currentId++;
	}
	public int getID() {
		return id;
	}
	public String getName() {
		return name;
	}
	public BigDecimal  getPriceUnit() {
			return priceunit;
	}
	public int 	  getStock() {
		return stock;
	}
	public void setPriceUnit(BigDecimal price_) {
		this.priceunit = price_;
	}
	public void  setStock(int stock_) {
		this.stock = stock_;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) 
        	return true;
        if (obj == null) 
        	return false;
        if (getClass() != obj.getClass()) 
        	return false;
        
        final Product other = (Product)obj;
        
        if (id != other.id) 
        	return false;
        
        return true;
    }
}
