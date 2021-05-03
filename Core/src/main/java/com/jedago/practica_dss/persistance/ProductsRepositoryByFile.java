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

import com.jedago.practica_dss.core.FirstProducts;
import com.jedago.practica_dss.core.Product;
import com.jedago.practica_dss.core.ProductType;

public class ProductsRepositoryByFile implements ProductsRepository {
	/**
	 * To check if the persistance file is created
	 * @return true if the persistance file is created
	 */
	public static boolean isFileCreated()
	{
		File ProductsFile = new File(Messages.getString("ProductsFile"));
		return ProductsFile.exists();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() throws Exception {
		List<Product> productList =  new ArrayList<Product>();
		if(isFileCreated()) //Si está creado el archivo, leemos su contenido
		{
			try {
				ObjectInputStream readProducts;
				readProducts = new ObjectInputStream(new FileInputStream(Messages.getString("ProductsFile")));
				productList = (List<Product>) readProducts.readObject();
				readProducts.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			

		}
		else //Si no, devolvemos los FirstProducts
		{
			productList = FirstProducts.getFirstProducts();
		}

		return productList;
	}

	@Override
	public List<Product> findAllStockAvailable() throws Exception {
		List<Product> stockAvailable = new ArrayList<Product>();
		Iterator<Product> i = findAll().iterator();
		while(i.hasNext())
		{
			Product p = i.next();
			if(p.getStock()>0)
			{
				stockAvailable.add(p);
			}
		}
		return stockAvailable;
	}
	
	@Override
	public List<ProductType> findAllTypes() throws Exception {
		List<ProductType> typeList = new ArrayList<ProductType>();

		for(Product p : findAll())
		{
			if(!typeList.contains(p.getType()))
				typeList.add(p.getType());
		}

		return typeList;
	}

	@Override
	public Optional<Product> findById(String id) throws Exception {
		boolean found = false;
		Product seekProduct = null, p;
		
		Iterator<Product> i = findAll().iterator();
		
		while(i.hasNext() && !found)
		{
			p = i.next();
			if(p.getID()==id) 
			{
				seekProduct = p;
				found = true;
			}
		}
		if(found)
			return Optional.of(seekProduct);
		else
			return Optional.empty();
	}
	
	@Override
	public Optional<ProductType> findTypeById(String id) throws Exception {
		List<ProductType> productTypes = this.findAllTypes();
		boolean found = false;
		ProductType seekType = null, t;
		
		Iterator<ProductType> i = productTypes.iterator();
		while(i.hasNext() && !found)
		{
			t = i.next();
			if(t.getId()==id) 
			{
				seekType = t;
				found = true;
			}
		}
		if(found)
			return Optional.of(seekType);
		else
			return Optional.empty();
	}
	
	@Override
	public List<Product> findAllStockAvailableByType(ProductType pt) throws Exception {
		List<Product> products = new ArrayList<Product>();
		for(Product p : findAllStockAvailable())
		{
			if(p.getType().getId().equals(pt.getId()))
				products.add(p);
		}
		return products;
	}

	@Override
	public void save(List<Product> productList) throws Exception {
		ObjectOutputStream writeProducts;
		try {
			writeProducts = new ObjectOutputStream(new FileOutputStream(Messages.getString("ProductsFile")));
			writeProducts.writeObject(productList);
			writeProducts.flush();
			writeProducts.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(Product p) throws Exception {
		List<Product> productList = new ArrayList<Product>();
		productList = findAll();
		productList.add(p);
		save(productList);
	}

	@Override
	public void delete(List<Product> productList) throws Exception {
		List<Product> saved = new ArrayList<Product>();
		saved = findAll();
		saved.removeAll(productList);
		save(saved);
	}

	@Override
	public void delete(Product p) throws Exception {
		List<Product> saved = new ArrayList<Product>();
		saved = findAll();
		saved.remove(p);
		save(saved);
	}

}
