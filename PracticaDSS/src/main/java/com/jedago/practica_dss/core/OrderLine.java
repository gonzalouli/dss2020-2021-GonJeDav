package com.jedago.practica_dss.core;

import java.math.BigDecimal;

/**
 * Una clase para representar una linea de un producto
 * dentro del pedido. Esta clase contiene un producto y su cantidad
 * a refleja en la OrderLine.
 * @version 1.0. 21/03/2021
 * @author Gonzalo Ulibarri
 */

public class OrderLine {
	
	Product product_;
	int amount;

 /** 
 * Crea pedido vacío con un id unico, una lista
 * de OrderLine vacía y una fecha determinada.
 * @param product_ producto  deseado a introducir en la OrderLine.
 * @param amount cantidad a introducir del producto product_.
 */
	public OrderLine(Product product_, int amount) 
	{
		this.product_ = product_;
		this.amount = amount;
	}
	
 /** 
 * Devuelve el producto que pertenede a una OrderLine determinada
 * @return Producto de la OrderLine.
 */
	public Product getProduct() 
	{
		return product_;
	}
	

 /** 
 * Introduce un nuevo producto en una OrderLine determinada.
 * @param newproduct nuevo producto a introducir.
 */
	public void setProduct(Product newproduct) 
	{
		product_ = newproduct;
		
	}
	
 /** 
 * Devuelve un string que representa el nombre del producto
 * que pertenede a la una OrderLine determinada.
 * @return Nombre del producto.
 */	
	public String getProductName() 
	{
		return product_.getName();
	}

 /** 
 * Devuelve la cantidad de producto product_ que tenemos
 * en una OrderLine determinada.
 * @return Cantidad de producto en la OrderLine.
 */
	public int getAmount() 
	{
		return amount;
	}
	
 /** 
 * Introduce una nueva cantidad del producto product_ 
 * en una OrderLine determinada.
 * @param q Nueva cantidad del producto en la OrderLine.
 */
	public void setAmount(int q)
	{
		assert(q>=0);
		
		this.amount = q;
		
	}

 /** 
 * Devuelve el coste total de la cantidad de todos del 
 * productos dentro de una OrderLine determinada.
 * @return Precio total de la OrderLine.
 */
	public BigDecimal getTotalPrice() 
	{
		BigDecimal coste = BigDecimal.ZERO;
		BigDecimal costetotal = BigDecimal.ZERO;
		coste = product_.getPriceUnit().multiply(new BigDecimal(amount));
		costetotal = costetotal.add(coste);
		return costetotal;			
	}

}
