package com.jedago.practica_dss.core;

import java.math.BigDecimal;

public class CashBox {
	private int nOrders;
	private BigDecimal total;
	
	public int getnOrders() {
		return nOrders;
	}
	
	public void setnOrders(int nOrders) {
		this.nOrders = nOrders;
	}
	
	public void incrementOrders() {
		this.nOrders = getnOrders()+1;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public void addtoTotal(BigDecimal inc) {
		this.total = total.add(inc);
	}
}
