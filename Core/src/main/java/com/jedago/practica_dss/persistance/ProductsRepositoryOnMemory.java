package com.jedago.practica_dss.persistance;

import java.util.List;

import com.jedago.practica_dss.core.Product;

public class ProductsRepositoryOnMemory implements ProductsRepository {
	private List<Product> products;
	
	@Override
	public List<Product> readProducts() throws Exception {
		return products;
	}

	@Override
	public void writeProducts(List<Product> productsList) throws Exception {
		this.products = productsList;
	}

}
