package com.jedago.practica_dss.persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.jedago.practica_dss.core.Order;

public class OrdersRepositoryByFile implements OrdersRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> readOrders() throws Exception {
		
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
	public void writeOrders(List<Order> orderList) throws Exception {
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
	
	/**
	 * To check if the persistance file is created
	 * @return true if the persistance file is created
	 */
	public static boolean isFileCreated()
	{
		File OrdersFile = new File(Messages.getString("OrdersFile"));
		return OrdersFile.exists();
	}

}
