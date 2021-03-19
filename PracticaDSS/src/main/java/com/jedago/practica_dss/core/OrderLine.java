package com.jedago.practica_dss.core;

import java.math.BigDecimal;

public class OrderLine {
	
	Product product_;
	int amount;

	public OrderLine(Product product_, int amount) {
		this.product_ = product_;
		this.amount = amount;
	}
	
	public Product getProduct() {
		return product_;
	}
	
	public String getProductName() {
		return product_.getName();
	}

	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int q){
		if(q >0)
			this.amount = q;
		else
			System.out.println("Cantidad no permitida...");
		// TODO Auto-generated method stub
		
	}

	public BigDecimal getTotalPrice() {
		BigDecimal coste = BigDecimal.ZERO;
		BigDecimal costetotal = BigDecimal.ZERO;
		coste = product_.getPriceUnit().multiply(new BigDecimal(amount));
		costetotal = costetotal.add(coste);
		return costetotal;
				
	}

}
