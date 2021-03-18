package com.jedago.practica_dss.core;
import static org.junit.Assert.assertEquals;

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
	}
	
//#########################################################
	public long getId_order() {
		return this.id_order;
	}
	public BigDecimal getPrice() {
		return this.price;
	}
	
	public Date getDate() {
		return date;
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
	
	public void setProduct(OrderLine currentOrderLine ) 
	{	
		this.OrderLineProduct.add(currentOrderLine);
		this.price.add(currentOrderLine.getProduct().getPriceUnit());
	}
	
	public void setNProducts(OrderLine currentOrderLine ) 
	{	
		this.OrderLineProduct.add(currentOrderLine);
		BigDecimal namount = BigDecimal.ZERO;
		namount = new BigDecimal(currentOrderLine.getAmount());
		this.price.add(currentOrderLine.getProduct().getPriceUnit().multiply(namount));
	}

//################ Operaciones Externas?#################
	
	
	public void addProductToOrder(Product currentProduct)
	{	
		for(OrderLine pivot : OrderLineProduct) {
			if(pivot.getProduct().getID() == currentProduct.getID()) {
				pivot.setAmount(pivot.getAmount()+1);
				BigDecimal namount = BigDecimal.ZERO;
				namount = new BigDecimal(pivot.getAmount()) ;
				this.price.add(pivot.getProduct().getPriceUnit());				
				return;
			}
		}
		OrderLine ol = new OrderLine(currentProduct,1);
		setProduct(ol);	
		
	}
	
	public void addProductToOrder(Product currentProduct , int cant)
	{	
		for(OrderLine pivot : OrderLineProduct) {
			if(pivot.getProduct().getID() == currentProduct.getID()) {
				pivot.setAmount(pivot.getAmount()+cant);
				BigDecimal namount = BigDecimal.ZERO;
				namount = new BigDecimal(pivot.getAmount()) ;
				this.price.add(pivot.getProduct().getPriceUnit().multiply(namount));
				return;
			}
		}

		OrderLine ol = new OrderLine(currentProduct,cant);
		setNProducts( ol );
	}
	
	public void deteteProductFromOrder(Product currentProduct, int cant) {
		
		for(OrderLine pivot : OrderLineProduct) {
			if(pivot.getProduct().getID() == currentProduct.getID()) {
				if(cant>0) {
					if(cant > pivot.getAmount() ) {
						
						deleteOrderlineFromOrder(pivot);
						System.out.println("Productos eliminados.");
						
					}else {
						
						int newamount = pivot.getAmount()-cant;
						pivot.setAmount(newamount);
						BigDecimal namount = BigDecimal.ZERO;
						namount = new BigDecimal(pivot.getAmount()) ;
						this.price.subtract(pivot.getProduct().getPriceUnit().multiply(namount));	
						
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
	