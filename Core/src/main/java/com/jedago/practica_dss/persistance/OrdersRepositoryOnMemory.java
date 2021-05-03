package com.jedago.practica_dss.persistance;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.jedago.practica_dss.core.Order;
import com.jedago.practica_dss.core.User;

public class OrdersRepositoryOnMemory implements OrdersRepository {
	List<Order> orders;

	@Override
	public List<Order> findAll() throws Exception {
		return orders;
	}
	
	@Override
	public Optional<Order> findById(int id) {
		
		boolean found = false;
		Order seekOrder = null, order;
		
		Iterator<Order> i = orders.iterator();
		
		while(i.hasNext() && !found)
		{
			order = i.next();
			if(order.getId_order()==id) 
			{
				seekOrder = order;
				found = true;
			}
		}
		
		if(found)
			return Optional.of(seekOrder);
		else
			return Optional.empty();
	}

	@Override
	public void save(List<Order> orderList) throws Exception {
		this.orders = orderList;
	}

	@Override
	public void add(Order o) throws Exception {
		this.orders.add(o);
	}

	@Override
	public void delete(List<Order> orderList) throws Exception {
		this.orders.removeAll(orderList);
	}

	@Override
	public void delete(Order o) throws Exception {
		this.orders.remove(o);
	}

	@Override
	public void update(String id, Order o) throws Exception {
		Optional<Order> toUpdate = this.findById(id); 
		if(!toUpdate.isPresent())
		{
			this.delete(toUpdate.get());
			this.add(o);
		}
	}
}
