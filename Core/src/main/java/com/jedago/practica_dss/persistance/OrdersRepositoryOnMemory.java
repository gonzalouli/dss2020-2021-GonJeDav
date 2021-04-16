package com.jedago.practica_dss.persistance;

import java.util.List;

import com.jedago.practica_dss.core.Order;

public class OrdersRepositoryOnMemory implements OrdersRepository {
	List<Order> orders;
	
	@Override
	public List<Order> readOrders() throws Exception {
		return orders;
	}

	@Override
	public void writeOrders(List<Order> orderList) throws Exception {
		this.orders = orderList;
	}
}
