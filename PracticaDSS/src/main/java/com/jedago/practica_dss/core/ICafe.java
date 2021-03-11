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
	public void newOrder();
	//Ver productos disponibles
	public void showProducts();
	//Añadir producto al pedido
	public void addProductToOrder();
	//Eliminar producto del pedido
	public void deleteProductFromOrder();
	//Finalizar pedido
	public void FinishOrder();
	//Consultar caja del día
	public void showCashBox();
}
