/**
 * 
 */
package com.jedago.practica_dss.persistance;

import java.util.List;

import com.jedago.practica_dss.core.Product;

/**
 * @author Jesus Serrano Gallan
 *
 */
public interface ProductsRepository {
	/**
	 * To get the list of available products
	 * @return A list with all the available products
	 * @throws Exception
	 */
	public List<Product> readProducts() throws Exception;
	
	/**
	 * To save the current list of products
	 * @param ProductList 
	 * @throws Exception
	 */
	public void writeProducts(List<Product> ProductList) throws Exception;
}
