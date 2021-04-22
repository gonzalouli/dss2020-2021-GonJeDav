package com.jedago.practica_dss.persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.jedago.practica_dss.core.Order;

public class OrdersRepositoryByFile implements OrdersRepository {
	
	/**
	 * To check if the persistance file is created
	 * @return true if the persistance file is created
	 */
	public static boolean isFileCreated()
	{
		File OrdersFile = new File(Messages.getString("OrdersFile"));
		return OrdersFile.exists();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAll() throws Exception {
		List<Order> orderList =  new ArrayList<Order>();
		
		if(isFileCreated()) //Si el archivo está creado lo leemos
		{
			ObjectInputStream readOrders;
			try {
				readOrders = new ObjectInputStream(new FileInputStream(Messages.getString("OrdersFile")));
				orderList = (List<Order>) readOrders.readObject();
				readOrders.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//Si no está creado, devolvemos la lista vacía
		return orderList;
	}

	@Override
	public Optional<Order> findById(int id) throws Exception {
		boolean found = false;
		Order seekOrder = null, order;
		List<Order> orders =  findAll();
		
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
		ObjectOutputStream writeOrders;
		try {
			writeOrders = new ObjectOutputStream(new FileOutputStream(Messages.getString("OrdersFile")));
			writeOrders.writeObject(orderList);
			writeOrders.flush();
			writeOrders.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(Order o) throws Exception {
		List<Order> orderList = new ArrayList<Order>();
		orderList = findAll();
		orderList.add(o);
		save(orderList);
	}

	@Override
	public void delete(List<Order> orderList) throws Exception {
		List<Order> saved = new ArrayList<Order>();
		saved = findAll();
		saved.removeAll(orderList);
		save(saved);
	}

	@Override
	public void delete(Order o) throws Exception {
		List<Order> saved = new ArrayList<Order>();
		saved = findAll();
		saved.remove(o);
		save(saved);
	}
	
}
