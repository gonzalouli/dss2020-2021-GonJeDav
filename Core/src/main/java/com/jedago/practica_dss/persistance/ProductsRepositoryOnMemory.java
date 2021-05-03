package com.jedago.practica_dss.persistance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.jedago.practica_dss.core.Product;
import com.jedago.practica_dss.core.ProductType;

public class ProductsRepositoryOnMemory implements ProductsRepository {
	private List<Product> products;

	@Override
	public List<Product> findAll() throws Exception {
		return products;
	}

	@Override
	public List<Product> findAllStockAvailable() throws Exception {
		List<Product> stockAvailable = new ArrayList<Product>();
		Iterator<Product> i = products.iterator();
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
		
		Iterator<Product> i = products.iterator();
		
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
			if(p.getType().equals(pt))
				products.add(p);
		}
		return products;
	}

	@Override
	public void save(List<Product> productList) throws Exception {
		this.products = productList;
	}

	@Override
	public void add(Product p) throws Exception {
		this.products.add(p);
	}

	@Override
	public void delete(List<Product> productList) throws Exception {
		this.products.removeAll(productList);
	}

	@Override
	public void delete(Product p) throws Exception {
		this.products.remove(p);
	}
}
