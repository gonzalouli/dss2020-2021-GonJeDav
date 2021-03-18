package com.jedago.practica_dss.core;
import java.util.*;

public class Order implements Iterable<OrderLine>
{
	public static long currentid = 0;
	private long id_order;
	private List<OrderLine> OrderLineProduct;

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
		price+=currentOrderLine.getAmount();
	}
	
	public void setNProducts(OrderLine currentOrderLine ) 
	{	
		this.OrderLineProduct.add(currentOrderLine);
		this.price += currentOrderLine.getProduct().getPriceUnit()*currentOrderLine.getAmount();
	}

//################ Operaciones �Externas?#################
	
//	public void showOrderProducts() {
//		
//		for(IOrderLine io : OrderLineProduct) {
//			System.out.println("ID_Pedido : "+ io.get(i).getProduct().getId_product());
//			System.out.println("Nombre : "+ io.get(i).getProduct().getName());
//			System.out.println("PrecioU : "+ io.get(i).getProduct().getPrice());
//			System.out.println("Stock : "+ io.getProduct().getStock());
//		}
//	}
//	
	public void addProductToOrder(Product currentProduct)
	{	
		for(OrderLine pivot : OrderLineProduct) {
			if(pivot.getProductName() == currentProduct.getName()) {
				pivot.setAmount(pivot.getAmount()+1);
				price+=pivot.getAmount();
				return;
			}
		}
		OrderLine ol = new OrderLine(currentProduct,1);
		setProduct(ol);	
		
	}
	
	public void addProductToOrder(Product currentProduct , int cant)
	{	
		for(OrderLine pivot : OrderLineProduct) {
			if(pivot.getProductName() == currentProduct.getName()) {
				pivot.setAmount(pivot.getAmount()+cant);
				this.price += pivot.getProduct().getPriceUnit()*pivot.getAmount();
				return;
			}
		}

		OrderLine ol = new OrderLine(currentProduct,cant);
		setNProducts( ol );
	}
	
	public void deteteProductFromOrder(Product currentProduct, int cant) {
		
		for(OrderLine pivot : OrderLineProduct) {
			if(pivot.getProductName() == currentProduct.getName()) {
				if(cant>0) {
					if(cant > pivot.getAmount() ) {
						deleteOrderlineFromOrder(pivot);
						System.out.println("Productos eliminados.");
					}else {
						int newamount = pivot.getAmount()-cant;
						pivot.setAmount(newamount);
						System.out.println("Eliminados "+cant+" productos, quedan "+newamount);
						this.price -= pivot.getProduct().getPriceUnit()*pivot.getAmount();
		
					}
				}
			}
		}	
	}
	
	public void deleteOrderlineFromOrder(OrderLine currentOrderLine) 
	{
		if (OrderLineProduct.remove(currentOrderLine))
			System.out.println("Producto eliminado");
		else
			System.out.println("Producto desconocido o inexistente en el pedido");
	}

	@Override	public Iterator<OrderLine> iterator() {
		return OrderLineProduct.iterator();
	}


	
}
	