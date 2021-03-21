package com.jedago.practica_dss.core;

import java.math.BigDecimal;

import java.util.*;

/**
 * Una clase para representar un pedido realizado en
 * la clase cafe. El pedido esta determinado por una 
 * id, una lista de OrderLine, un precio total
 * del pedido y una fecha de apertura.
 * @version 1.0. 21/03/2021
 * @author Gonzalo Ulibarri
 */
public class Order implements Iterable<OrderLine>
{
	public static long currentid = 1;
	private long id_order;
	private List<OrderLine> OrderLineProduct;
	private BigDecimal price;
	private Date date;
	
	 /** 
     * Crea pedido vacío con un id unico, una lista
     * de OrderLine vacía y una fecha determinada.
     */
	public Order(){
		this.id_order = currentid;
		currentid++;
		this.price = BigDecimal.ZERO;
		this.date = new Date();
		OrderLineProduct = new ArrayList<OrderLine>();
	}
	
//#########################################################
	

	 /** 
     * Devuelve el id de un pedido determinado
	 * @return El id del pedido.
	 */
	public long getId_order() {
		return this.id_order;
	}
	
	
	 /** 
     * Devuelve el precio de un pedido determinado.
	 * @return El precio del pedido.
	 */
	public BigDecimal getPrice() {
		return this.price;
	}
	
	 /** 
     * Comprueba la fecha en la que se creó el pedido.
	 * @return El id del pedido.
	 */
	public Date getDate() {
		return this.date;
	}

 /** 
 * Indica si el pedido está vacio.
 * @return True si el pedido está vacio, false en caso contrario.
 */
	public boolean isEmpty() {
		return OrderLineProduct.isEmpty();
	}

 /** 
 * Devuelve el tamaño de un pedido determinado.
 * @return El tamaño de un pedido.
 */
	public int size() {
		return OrderLineProduct.size();
	}
	
	
 /** 
 * Devuelve la lista de OrderLine que contiene el producto.
 * @return Lista de OrderLine del producto.
	 */	public List<OrderLine> getProducts() 
	{
		return this.OrderLineProduct;
	}
	
	 
 /** 
 * Introduce una OrderLine en el pedido.
 * @param currentOrderLine OrderLine a introducir en el pedido.
 */
	public void setProducts(OrderLine currentOrderLine ) 
	{	
		this.OrderLineProduct.add(currentOrderLine);
		BigDecimal namount = BigDecimal.valueOf(currentOrderLine.getAmount());
		BigDecimal total = currentOrderLine.getProduct().getPriceUnit().multiply(namount);
		this.price = this.price.add(total);
	}

	
	 /** 
     * Introduce una unidad de un producto determinado en el pedido.
	 * @param currentProduct define el producto que queremos introducir en el pedido.
	 */
	public void addProductToOrder(Product currentProduct)
	{	
		/*for(OrderLine pivot : OrderLineProduct) {
			if(pivot.getProduct().getID() == currentProduct.getID()) {
				pivot.setAmount(pivot.getAmount()+1);
				this.price = this.price.add(pivot.getProduct().getPriceUnit());				
				return;
			}
		}
		
		OrderLine ol = new OrderLine(currentProduct,1);
		setProducts(ol);	*/
		this.addProductToOrder(currentProduct, 1);
		
	}
	
	 /** 
     * Introduce un determinado número de unidades del producto en el pedido.
	 * @param currentProduct define el producto que queremos introducir en el pedido.
	 * @param cant determina la cantidad de producto que queremos introducir.
	 */
	public void addProductToOrder(Product currentProduct , int cant)
	{	
		for(OrderLine pivot : OrderLineProduct) {
			if(pivot.getProduct().getID() == currentProduct.getID()) {
				pivot.setAmount(pivot.getAmount()+cant);
				BigDecimal namount = new BigDecimal(pivot.getAmount()) ;
				this.price = this.price.add(pivot.getProduct().getPriceUnit().multiply(namount));
				return;
			}
		}

		OrderLine ol = new OrderLine(currentProduct,cant);
		setProducts(ol);
	}
	
	 /** 
     * Elimina una cantidad determinada de un producto dado en el pedido. Si la cantidad 
     * es mayor o igual a la cantidad actual del producto en el OrderLine se
     * elimina el producto de la lista de OrderLine.
	 * @param currentProduct define el producto que queremos eliminar del pedido.
	 * @param cant cantidad del producto a eliminar.
	 */
	public void deleteProductFromOrder(Product currentProduct, int cant) {
		
		///for(OrderLine pivot : OrderLineProduct) {
		//OrderLineProduct.get(i)
		OrderLine ol;
		Iterator<OrderLine> it = OrderLineProduct.iterator();
		while(it.hasNext()) { 
			ol = it.next();
			if(ol.getProduct().getID() == currentProduct.getID()) {
				if(cant>0) {
					BigDecimal newamount = new BigDecimal(ol.getAmount()-cant);
					if(cant >= ol.getAmount() ) {
						
						this.price = this.price.subtract(ol.getProduct().getPriceUnit().multiply(new BigDecimal(cant)));
						deleteOrderlineFromOrder(it);
						
					}else {
						
						ol.setAmount( newamount.intValue());
						newamount = new BigDecimal(ol.getAmount()) ;
						this.price = this.price.subtract(ol.getProduct().getPriceUnit().multiply(new BigDecimal(cant)));
						
					}
				}
			}
		}	
	}
	
	 /** 
     * Elimina una OrderLine determinada de la lista de OrderLine del pedido.
	 * @param currentOrderLine define la OrderLine que queremos eliminar del pedido.
	 */
	public void deleteOrderlineFromOrder(OrderLine currentOrderLine) 
	{
		OrderLineProduct.remove(currentOrderLine);
		
	}
	
	/** 
     * Elimina una OrderLine determinada de la lista de OrderLine del pedido utilizando un iterador.
	 * @param it Iterador que establece una OrderLine determinada en la lista de
	 * OrderLine.
	 */
	public void deleteOrderlineFromOrder(Iterator<OrderLine> it) 
	{
		it.remove();
		
	}
	
	/** 
     * Devuelve un iterador del tipo OrderLine.
	 * @return Iterador de la lista OrderLineProduct.
	 */
	
	@Override
	public Iterator<OrderLine> iterator() {
		return OrderLineProduct.iterator();
	}
	
	
}
	