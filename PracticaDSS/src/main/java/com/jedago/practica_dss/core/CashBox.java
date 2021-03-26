package com.jedago.practica_dss.core;

import java.math.BigDecimal;

public class CashBox {
	private int nOrders;
	private BigDecimal total;
	
	public CashBox() {
		this.nOrders = 0;
		this.total = BigDecimal.ZERO;
	}

	/**
	 * Return the number of registered orders
	 * @return number of registered orders
	 */
	public int getnOrders() {
		return nOrders;
	}
	
	/**
	 * Set the number of registered orders
	 * @param nOrders number of orders to set
	 */
	public void setnOrders(int nOrders) {
		this.nOrders = nOrders;
	}
	
	/**
	 * Increment in a unit the nnumber of registered orders
	 */
	public void incrementOrders() {
		this.nOrders = getnOrders()+1;
	}
	
	/**
	 * Returns the total money earned
	 * @return total money earned
	 */
	public BigDecimal getTotal() {
		return total;
	}
	
	/** 
	 * Set the total money earned
	 * @param total to set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	/**
	 * Increment the total money earned
	 * @param inc amount to increase the total 
	 */
	public void addtoTotal(BigDecimal inc) {
		this.total = total.add(inc);
	}
}
