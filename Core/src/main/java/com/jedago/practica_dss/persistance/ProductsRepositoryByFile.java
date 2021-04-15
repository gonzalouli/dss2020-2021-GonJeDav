package com.jedago.practica_dss.persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.jedago.practica_dss.core.FirstProducts;
import com.jedago.practica_dss.core.Product;

public class ProductsRepositoryByFile implements ProductsRepository {
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> readProducts() throws Exception {
		
		List<Product> ProductList =  new ArrayList<Product>();
		if(isFileCreated()) //Si está creado el archivo, leemos su contenido
		{
			try {
				ObjectInputStream readProducts;
				readProducts = new ObjectInputStream(new FileInputStream(Messages.getString("ProductsFile")));
				ProductList = (List<Product>) readProducts.readObject();
				readProducts.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else //Si no, devolvemos los FirstProducts
		{
			ProductList = FirstProducts.getFirstProducts();
		}
		
		return ProductList;
	}

	@Override
	public void writeProducts(List<Product> ProductList) throws Exception {
		ObjectOutputStream writeProducts;
		try {
			writeProducts = new ObjectOutputStream(new FileOutputStream(Messages.getString("ProductsFile")));
			writeProducts.writeObject(ProductList);
			writeProducts.flush();
			writeProducts.close();
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
		File ProductsFile = new File(Messages.getString("ProductsFile"));
		return ProductsFile.exists();
	}

}
