package com.jedago.practica_dss.core;

class CashBox implements ICashBox {
	//private int id_;
	//private static int nCashBoxes;
	//private Date date_;
	//private ArrayList<Order> orders_;
	private float total_;
	private int nOrders_;
	
	CashBox()
	{
		this.total_ = 0f;
		this.nOrders_ = 0;
	}

	public float getTotal() {
		return total_;
	}

	/*public void setTotal(float total_) {
		this.total_ = total_;
	}*/

	public int getnOrders_() {
		return nOrders_;
	}

	/*public void setnOrders_(int nOrders_) {
		this.nOrders_ = nOrders_;
	}*/
	
	public void addOrder(Order o) {
		this.nOrders_++;
		//this.total_+= sumar el total del pedido
	}

	

}
