package com.jedago.practica_dss.core;

public interface IOrderLine {
	public String getProductName();
	public int getAmount();
	public void setAmount(int q);
	public float getTotalPrice();
}
