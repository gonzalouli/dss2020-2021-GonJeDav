package com.jedago.practica_dss.core;

class OrderLine implements IOrderLine{
	
	IProduct product_;
	int amount;

	public OrderLine(IProduct product_, int amount) {
		this.product_ = product_;
		this.amount = amount;
	}

	@Override
	public String getProductName() {
		// return product.getName();
		return null;
	}

	@Override
	public int getAmount() {
		return amount;
	}
	
	@Override
	public void setAmount(int q){
		this.amount = q;
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getTotalPrice() {
		// return product.getPrice*amount;
		return 0;
	}

}
