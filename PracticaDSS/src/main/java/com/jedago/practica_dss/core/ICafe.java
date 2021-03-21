/**
 * 
 */
package com.jedago.practica_dss.core;

import java.util.List;

/**
 * @author jeseg
 *
 */
public interface ICafe {
	
	public Order newOrder();
	//Productos disponibles
	public List<Product> getAvailableProducts();
	//Añadir producto al pedido en curso
	public void addProductToOrder(Order ord, Product p);
	//Añadir producto al pedido en curso
	public void addProductToOrder(Order ord, Product p, int c);
	//Eliminar producto del pedido en curso
	public void deleteProductFromOrder(Order ord, Product p);
	//Eliminar producto del pedido en curso
	public void deleteProductFromOrder(Order ord, Product p, int c);
	//Finalizar pedido
	public void FinishOrder(Order ord);
	//Consultar caja del día 
	public CashBox getCashBox();
}
