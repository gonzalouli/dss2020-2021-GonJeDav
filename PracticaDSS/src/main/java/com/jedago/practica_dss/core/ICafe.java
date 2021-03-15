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
	//Añadir producto al pedido en curso
	public void addProductToOrder(IProduct p);
	//Eliminar producto del pedido en curso
	public void deleteProductFromOrder(IProduct p);
	//Finalizar pedido
	public void FinishOrder();
	//Consultar caja del día
	public void showCashBox();
}
