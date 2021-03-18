package com.jedago.practica_dss.core;

public class OrderLine{
	
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

	public float getTotalPrice() {
		// return product.getPrice*amount;
		return 0;
	}

}
