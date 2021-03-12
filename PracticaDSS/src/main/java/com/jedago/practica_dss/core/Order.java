package com.jedago.practica_dss.core;
import java.lang.*;
import java.util.*;

public class Order implements IOrder 
{
	private static long currentid = 0;
	private long id_order;
	private List<IOrderLine> OrderLineProduct;

	//private enum payment_method {CARD,CASH};
	private double price;
	private Date date;
	//private enum status {OPEN, CLOSED, IN_PROCESS};

//#########################################################
	
	public Order(){
		this.id_order = currentid+1;
		currentid++;
		this.price = 0;
		this.date = new Date();
	}
	
//#########################################################
	@Override
	public long getId_oder() {
		return this.id_order;
	}
	@Override
	public double getPrice() {
		return this.price;
	}
	
	@Override
	public Date getDate() {
		return date;
	}

//#########################################################
	@Override
	public List<IOrderLine> getProducts() 
	{
		return this.OrderLineProduct;
	}
	
	@Override
	public void setProduct(IOrderLine currentOrderLine ) 
	{	
		this.OrderLineProduct.add(currentOrderLine);
		price+=currentOrderLine.getAmount();
	}
	
	@Override
	public void setNProducts(IOrderLine currentOrderLine ) 
	{	
		this.OrderLineProduct.add(currentOrderLine);
		this.price += currentOrderLine.getProduct().getPriceUnit()*currentOrderLine.getAmount();
	}

//################ Operaciones �Externas?#################
	
	@Override
	public void showProducts() {
		
		for( int i = 0; i<OrderLineProduct.size() ; i++) {
			System.out.println("ID_Pedido : "+ this.OrderLineProduct.get(i).getProduct().getId_product());
			System.out.println("Nombre : "+ this.OrderLineProduct.get(i).getProduct().getName());
			System.out.println("PrecioU : "+ this.OrderLineProduct.get(i).getProduct().getPrice());
			System.out.println("Stock : "+ this.OrderLineProduct.get(i).getProduct().getStock());
		}
	}
	
	@Override
	public void addProductToOrder(IProduct currentProduct)
	{	
		for(IOrderLine pivot : OrderLineProduct) {
			if(pivot.getProductName() == currentProduct.getName()) {
				pivot.setAmount(pivot.getAmount()+1);
				return;
			}
		}
		OrderLine ol = new OrderLine(currentProduct,1);
		setProduct(ol);	
		
	}
	
	@Override
	public void addProductToOrder(IProduct currentProduct , int cant)
	{	
		for(IOrderLine pivot : OrderLineProduct) {
			if(pivot.getProductName() == currentProduct.getName()) {
				pivot.setAmount(pivot.getAmount()+cant);
				return;
			}
		}

		OrderLine ol = new OrderLine(currentProduct,cant);
		setNProducts( ol );
	}
	
	@Override
	public void deteteProductFromOrder(IOrderLine currentOrderLine, int cant) {
		if(cant>0) {
			if(cant > currentOrderLine.getAmount() ) {
				deleteOrderlineFromOrder(currentOrderLine);
				System.out.println("Productos eliminados.");
			}else {
				int newamount = currentOrderLine.getAmount()-cant;
				currentOrderLine.setAmount(newamount);
				System.out.println("Eliminados "+cant+" productos, quedan "+newamount);
				this.price -= currentOrderLine.getProduct().getPriceUnit()*currentOrderLine.getAmount();

			}
		}
	}
	
	@Override
	public void deleteOrderlineFromOrder(IOrderLine currentOrderLine) 
	{
		if (OrderLineProduct.remove(currentOrderLine))
			System.out.println("Producto eliminado");
		else
			System.out.println("Producto desconocido o inexistente en el pedido");
	}




}
	








	


