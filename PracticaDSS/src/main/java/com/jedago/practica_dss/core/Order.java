package com.jedago.practica_dss.core;
import java.lang.*;
import java.util.*;

public class Order implements IOrder 
{
	private static long currentid = 0;
	private long id_order;
<<<<<<< HEAD
	private List<IOrderLine> OrderLineProduct;
=======
	private List<IOrderLine> products;
>>>>>>> cbc2b1eac3334efe9efff832aae208de8ba1ee06
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
	
	public long getId_oder() {
		return this.id_order;
	}
	
	public double getPrice() {
		return this.price;
	}
	
//#########################################################
	
	public List<IOrderLine> getProducts() 
	{
		return this.OrderLine.product_;
	}
	
	public void setProduct(Product currentProduct ) 
	{	
		OrderLine OL = new OrderLine(currentProduct,1);
		this.OrderLineProduct.add(OL);
		price=+currentProduct.getVaule();
	}
	
	public void setNProducts(Product currentProduct , int cant ) 
	{
		OrderLine OL = new OrderLine(currentProduct,cant);
		this.OrderLineProduct.add(OL);
	}

//################ Operaciones #################

	public void showProducts() {
		
		for( int i = 0; i<products.size() ; i++) {
			System.out.println("ID_Pedido : "+ this.products.get(i).getId_product());
			System.out.println("Nombre : "+ this.products.get(i).getName() );
			System.out.println("PrecioU : "+ this.products.get(i).getPrice);
			System.out.println("Stock : "+ this.products.get(i).getStock);
		}
	}

	public void addProductToOrder(Product currentProduct)
	{
		setProduct(currentProduct);	
		
	}
	
	public void addProductToOrder(Product currentProduct , int cant)
	{
		setNProducts( currentProduct ,  cant );
	}

	public void deleteProductFromOrder(Product currentProduct) 
	{
		if (products.remove(currentProduct))
			System.out.println("Producto eliminado");
		else
			System.out.println("Producto desconocido o inexistente en el pedido");
	}
	
	public void finishOrder() {
		CashBox.addOrder(this);		
	}

	@Override
	public void setProducts(Product currenProduct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProductToOrder() {
		// TODO Auto-generated method stub
		
	}
	
}
	








	


