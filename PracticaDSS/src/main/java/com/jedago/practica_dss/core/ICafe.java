/**
 * 
 */
package com.jedago.practica_dss.core;

/**
 * @author jeseg
 *
 */
public interface ICafe {
	//Crear nuevo pedido
	public Order newOrder();
	//Ver productos disponibles
	public void showProducts();
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
	public void showCashBox();
}
