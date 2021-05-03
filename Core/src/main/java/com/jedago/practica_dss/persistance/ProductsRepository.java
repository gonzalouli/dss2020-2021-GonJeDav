/**
 * 
 */
package com.jedago.practica_dss.persistance;

import java.util.List;
import java.util.Optional;

import com.jedago.practica_dss.core.Product;
import com.jedago.practica_dss.core.ProductType;

/**
 * @author Jesus Serrano Gallan
 *
 */
public interface ProductsRepository {
	/**
	 * To get the list of all registered products
	 * @return A list with all the available products
	 * @throws Exception
	 */
	public List<Product> findAll() throws Exception;
	
	/**
	 * To get the list of all registered products that have some stock
	 * @return A list with all the available products with stock
	 * @throws Exception
	 */
	public List<Product> findAllStockAvailable() throws Exception;
	
	/**
	 * To get the list of all registered product types
	 * @return A list with all the available product types
	 * @throws Exception
	 */
	public List<ProductType> findAllTypes() throws Exception;
	
	/**
	 * To get a product from its id
	 * @param id
	 * @return The product with that id
	 * @throws Exception
	 */
	public Optional<Product> findById(String id) throws Exception;
	
	/**
	 * To get a product Type from its id
	 * @param id
	 * @return The productType with that id
	 * @throws Exception
	 */
	public Optional<ProductType> findTypeById(String id) throws Exception;
	
	/**
	 * To get the list of all registered products that have some stock from its type
	 * @param pt The desired productType
	 * @return A list with all the available products of the desired type
	 * @throws Exception
	 */
	public List<Product> findAllStockAvailableByType(ProductType pt) throws Exception;
	
	/**
	 * To save the products to the repository, overwriting the previous ones
	 * @param ProductList The list of products to save
	 * @throws Exception
	 */
	public void save(List<Product> productList) throws Exception;
	
	/**
	 * To save just one product in the repository, adding it to the previous ones
	 * @param p product to save
	 * @throws Exception
	 */
	public void add(Product p) throws Exception;
	
	/**
	 * To delete several products from the repository
	 * @param productList products to delete
	 * @throws Exception
	 */
	public void delete(List<Product> productList) throws Exception; 
	
	/**
	 * To delete a single product from the repository
	 * @param p product to delete
	 * @throws Exception
	 */
	public void delete(Product p) throws Exception; 
}
