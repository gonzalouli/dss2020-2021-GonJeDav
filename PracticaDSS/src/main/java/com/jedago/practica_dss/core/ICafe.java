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
	public IOrder newOrder();
	//Ver productos disponibles
	public void showProducts();
	//Añadir producto al pedido en curso
	public void addProductToOrder(IOrder ord, IProduct p);
	//Añadir producto al pedido en curso
	public void addProductToOrder(IOrder ord, IProduct p, int c);
	//Eliminar producto del pedido en curso
	public void deleteProductFromOrder(IOrder ord, IProduct p);
	//Eliminar producto del pedido en curso
	public void deleteProductFromOrder(IOrder ord, IProduct p, int c);
	//Finalizar pedido
	public void FinishOrder();
	//Consultar caja del día
	public void showCashBox();
}
