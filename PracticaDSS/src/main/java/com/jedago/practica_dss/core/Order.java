package com.jedago.practica_dss.core;

import java.math.BigDecimal;
import java.util.*;

public class Order implements Iterable<OrderLine>
{
	public static long currentid = 1;
	private long id_order;
	private List<OrderLine> OrderLineProduct;

	//private enum payment_method {CARD,CASH};
	private BigDecimal price;
	private Date date;
	//private enum status {OPEN, CLOSED, IN_PROCESS};

//#########################################################
	
	public Order(){
		this.id_order = currentid;
		currentid++;
		this.price = BigDecimal.ZERO;
		this.date = new Date();
		OrderLineProduct = new ArrayList<OrderLine>();
	}
	
//#########################################################
	public long getId_order() {
		return this.id_order;
	}
	public BigDecimal getPrice() {
		return this.price;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public boolean isEmpty() {
		return OrderLineProduct.isEmpty();
	}
	
	public int size() {
		return OrderLineProduct.size();
	}
	
	
//#########################################################
	public List<OrderLine> getProducts() 
	{
		return this.OrderLineProduct;
	}
	
	public void setProducts(OrderLine currentOrderLine ) 
	{	
		this.OrderLineProduct.add(currentOrderLine);
		BigDecimal namount = BigDecimal.valueOf(currentOrderLine.getAmount());
		BigDecimal total = currentOrderLine.getProduct().getPriceUnit().multiply(namount);
		this.price = this.price.add(total);
	}

//################ Operaciones Externas?#################
	
	
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
	
	public void deleteProductFromOrder(Product currentProduct, int cant) {
		
		///for(OrderLine pivot : OrderLineProduct) {
		//OrderLineProduct.get(i)
		for(int i =0 ; i<OrderLineProduct.size() ; i++) {
			if(OrderLineProduct.get(i).getProduct().getID() == currentProduct.getID()) {
				if(cant>0) {
					if(cant >= OrderLineProduct.get(i).getAmount() ) {
						
						deleteOrderlineFromOrder(OrderLineProduct.get(i));
						System.out.println("Productos eliminados.");
						
					}else {
						
						int newamount = OrderLineProduct.get(i).getAmount()-cant;
						OrderLineProduct.get(i).setAmount(newamount);
						BigDecimal namount = BigDecimal.ZERO;
						namount = new BigDecimal(OrderLineProduct.get(i).getAmount()) ;
						this.price.subtract(OrderLineProduct.get(i).getProduct().getPriceUnit().multiply(namount));	
						
					}
				}
			}
		}	
	}
	
	public void deleteOrderlineFromOrder(OrderLine currentOrderLine) 
	{
		OrderLineProduct.remove(currentOrderLine);
	}
	
	@Override	
	public Iterator<OrderLine> iterator() {
		return OrderLineProduct.iterator();
	}
	
	
}
	