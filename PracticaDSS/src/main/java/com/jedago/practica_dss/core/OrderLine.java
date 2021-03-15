package com.jedago.practica_dss.core;

class OrderLine implements IOrderLine{
	
	IProduct product_;
	int amount;

	public OrderLine(IProduct product_, int amount) {
		this.product_ = product_;
		this.amount = amount;
	}
	
	public IProduct getProduct() {
		return product_;
	}
	
	@Override
	public String getProductName() {
		return product_.getName();
	}

	@Override
	public int getAmount() {
		return amount;
	}
	
	@Override
	public void setAmount(int q){
		if(q >0)
			this.amount = q;
		else
			System.out.println("Cantidad no permitida...");
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getTotalPrice() {
		// return product.getPrice*amount;
		return 0;
	}

}
