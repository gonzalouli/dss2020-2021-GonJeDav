package com.jedago.practica_dss.cli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.jedago.practica_dss.core.*;
import com.jedago.practica_dss.core.exceptions.NoStockException;
import com.jedago.practica_dss.core.exceptions.VoidOrderException;
/**@author Gonzalo Ulibarri Garcia
 * @author Jesús Serrano Gallán
 *@version 1.0
 */

public class Screen   {
	
	private static Scanner sc = new Scanner(System.in);
	private static Scanner scCantidad = new Scanner(System.in);
	private ICafe cafe;
	
	public Screen(ICafe cafe)
	{
		this.cafe = cafe;
	}
	
	public void run() throws Exception 
	{			
		//Loop principal
		mainScreen(cafe);
	}
	
	 /** 
     * Show main screen with a CLI interface.
	 * @param currentCafe Defines the interface ICafe to operate with,
	 * @throws Exception 
	 */
	public static void mainScreen(ICafe currentCafe) throws Exception 
	{	

		String option;
		do 
		{
			System.out.println("\n\n"); 

			System.out.println("Software de Cafeteria UCA");  
			System.out.println("--------------------------------------");  
			System.out.println("1. Crear pedido");  
			System.out.println("2. Consultar Caja de hoy");  
			System.out.println("Q. Salir");  
			System.out.println("--------------------------------------");  
			System.out.println("Introduzca una opción:");  
			option = sc.nextLine();
			
			switch(option) 
			{

				case "1":	try{
								Order newOrder = currentCafe.newOrder();
								currentOrder(currentCafe, newOrder);
							}catch(Exception e) {
							System.out.println("Excepcion producida, pedido sin efecto");}
					break;
				case "2": 	viewCashBox(currentCafe.getTodayCashBox());
					break;
				case "q":
				case "Q": return;
					
				default: System.out.println("Introduzca una opción valida... ");  
			}
			
		}while(!option.equalsIgnoreCase("Q"));
	}
	
	 /** 
     * Operations that can place an order 
	 * @param currentCafe Defines the interface ICafe to operate with.
	 * @param currentOrder Defines the current order opened
	 * @throws Exception 
	 */
	public static void currentOrder(ICafe currentCafe, Order currentOrder) throws Exception 
	{	
		
		String option; 
		do 
		{
			System.out.println("\n\n"); 

			System.out.println("Pedido en curso ("+currentOrder.getId_order()+")");    
			System.out.println("--------------------------------------");  
			System.out.println("1. Añadir producto");  
			System.out.println("2. Eliminar producto");  
			System.out.println("3. Finalizar pedido");  
			System.out.println(".....");  
			System.out.println("R. Volver a la pantalla anterior");  
			System.out.println("--------------------------------------");  
			System.out.println("Introduzca una opción:");  
			
			option = sc.nextLine();

			switch(option) 
			{
			case "1":  selectProductType(currentCafe, currentOrder);
				break;
			case "2":  deleteProductFromCurrentOrder(currentCafe, currentOrder);
				break;
			case "3":  if(!currentOrder.isEmpty()) {
							finishCurrentOrder(currentCafe, currentOrder);
					   		return;
						}else
							System.out.println("Su pedido esta vacio, no se puede terminar un pedido vacio...");
						break;

			case "R":
			case "r":
				break;
			default: System.out.println("Introduzca una opción valida");  
			}		
		}while(!option.equalsIgnoreCase("R") );	

	}


	 /** 
     * Select the type of the product to show. 
	 * @param currentCafe Defines the interface ICafe to operate with.
	 * @param currentOrder Defines the current order opened
	 * @throws IOException 
	 */
	public static void selectProductType(ICafe currentCafe, Order currentOrder) throws IOException {

		int convertToInt = 0;
		String option;
		int index;
		List<ProductType> productTypeList = currentCafe.getAvailableProductTypes();

		do {
			index=1;
			System.out.println("\n\n"); 

			System.out.println("Tipos de producto");
			System.out.println("----------------------------------");
			
			for(ProductType iter: productTypeList) {
				System.out.println(index+". "+iter.getTypeName());
				++index;
			}
			System.out.println("......");
			System.out.println("R. Volver a la pantalla anterior");
			System.out.println("--------------------------------------");  
			System.out.println("Introduzca una opción:");  
		
			option = sc.nextLine();

			if( !option.matches("[a-zA-Z]") && !option.equals("") && !option.equals(" ")  ) {

				
				convertToInt = Integer.parseInt(option);
			
				if(convertToInt>0 && convertToInt<=index) {
					giveProduct( currentCafe,  currentOrder, productTypeList.get(convertToInt-1));
					return;
				}
			else
				if( !option.equalsIgnoreCase("R"))
					System.out.println("Eleccion invalida, pruebe otra vez...");	
			}
		}while( !option.equalsIgnoreCase("R") );
		
	}
		
	
	 /** 
     * Operations that is used to select a product. 
	 * @param currentCafe Defines the interface ICafe to operate with.
	 * @param currentOrder Defines the current order opened
	 * @param productType Defines the type of the product showed.
	 * @throws IOException 
	 */
	public static void giveProduct(ICafe currentCafe, Order currentOrder, ProductType productType) throws IOException 
	{

		String option;
		int convertToInt = 0;
		int index;
		int cant=1;
		
		List<Product> productGiven = new ArrayList<Product>();
		
		productGiven = currentCafe.getAvailableProductsbyType(productType); 
		
		do {
			System.out.println("\n\n"); 
			index = 1;
			System.out.println("Productos:");
			
			for(Product iter : productGiven) 
			{
				System.out.println(index+". "+iter.getName()+" ("+iter.getPrice()+" euros) Stock: "+iter.getStock()+" uds." + "(Tipo: " + iter.getType().getTypeName() + ")");
				++index;
			}
			
			System.out.println("......");
			System.out.println("R. Volver a la pantalla anterior");
			System.out.println("--------------------------------------"); 
			System.out.println("Introduzca una opción:"); 
			option = sc.nextLine();

			if( !option.matches("[a-zA-Z]") && !option.equals("")  && !option.equals(" ")    ) {

				
				convertToInt = Integer.parseInt(option);
			
				if(convertToInt>0 && convertToInt<index) {
					try {
							do{ 
								System.out.println("Introduzca la cantidad:"); 
								cant = scCantidad.nextInt();
								if(cant<1 || cant>productGiven.get(convertToInt-1).getStock())
									System.out.println("Cantidad invalida, pruebe con otra...");
								
							}while(cant<1 || cant>productGiven.get(convertToInt-1).getStock() );
						
						currentCafe.addProductToOrder(currentOrder, productGiven.get(convertToInt-1), cant);
						return;
						
					} catch (NoStockException e) {
						e.printStackTrace();
					}
				}else
					System.out.println("El producto elegido no existe, pruebe otra vez"); 
			}else
					if( !option.equalsIgnoreCase("R"))
						System.out.println("Eleccion invalida, pruebe otra vez...");
		}while( !option.equalsIgnoreCase("R")  );	
	}
	
	
	 /** 
     * Function to delete one or more products from an order.
	 * @param currentCafe Defines the interface ICafe to operate with.
	 * @param currentOrder Defines the current order opened
	 */
	public static void deleteProductFromCurrentOrder(ICafe currentCafe, Order currentOrder) 
	{
		int index;
		String option;
		int convertToInt = 0;
		int cant =1;
		
		do {
			System.out.println("\n\n"); 
			index = 1;
			System.out.println("Eliminar producto del pedido");
			List<OrderLine> productsInOrder = currentOrder.getProducts();
			
			for(OrderLine iter : productsInOrder) 
			{
				if(iter.getAmount()==1)
					System.out.println(index+". "+iter.getProductName()+" ("+iter.getAmount()+"ud x "+iter.getProduct().getPrice()+" euros)");
				else
					System.out.println(index+". "+iter.getProductName()+" ("+iter.getAmount()+"uds x "+iter.getProduct().getPrice()+" euros)");

				++index;
			}
			
			System.out.println("......");
			System.out.println("R. Volver a la pantalla anterior");
			System.out.println("--------------------------------------"); 
			System.out.println("Introduzca una opción:"); 
			
			option = sc.nextLine();

			if( !option.matches("[a-zA-Z]") && !option.equals("") && !option.equals(" ") ) {

				
				convertToInt = Integer.parseInt(option);
			
				if(convertToInt>0 && convertToInt<index) {
					do{ 
						System.out.println("Introduzca la cantidad a eliminar:"); 
						cant = scCantidad.nextInt();
					}while(cant<0);
					
					currentCafe.deleteProductFromOrder(currentOrder, productsInOrder.get(convertToInt-1).getProduct()  , cant);
				
				}else
						System.out.println("No existe ese producto en el pedido, pruebe otra vez...");
			}else if( !option.equalsIgnoreCase("R"))
						System.out.println("Eleccion invalida, pruebe otra vez...");
		}while( !option.equalsIgnoreCase("R"));
	}
	
	 /** 
     * Function to decide if the client want to finish the order.
	 * @param currentCafe Defines the interface ICafe to operate with.
	 * @param currentOrder Defines the current order opened
	 * @throws Exception 
	 */
	public static void finishCurrentOrder(ICafe currentCafe, Order currentOrder) throws Exception 
	{	System.out.println("\n\n");
		String option;
		int index=0;
		
		List<OrderLine> productsInOrder = currentOrder.getProducts();
		System.out.println("Productos del pedido");
		System.out.println("-------------------------------");

		for(OrderLine iter : productsInOrder) 
		{
			if(iter.getAmount()==1)
				System.out.println(++index+". "+iter.getProductName()+" ("+iter.getAmount()+"ud x "+iter.getProduct().getPrice()+" euros)");
			else
				System.out.println(++index+". "+iter.getProductName()+" ("+iter.getAmount()+"uds x "+iter.getProduct().getPrice()+" euros)");
		}
		
		
		do {
			System.out.println("\nPedido en curso ("+currentOrder.getId_order()+")"); 		
			System.out.println("-----------------------------------"); 		
			System.out.println("Total a pagar: "+currentOrder.getPrice()+" €"); 		
			System.out.println("1. Pagar y finalizar pedido");
			System.out.println("R. Volver a la pantalla anterior");
	
			option = sc.nextLine();
			switch(option) 
			{
			case "1": try {
						if( !currentOrder.isEmpty() ) {
								payAndFinishOrder(currentCafe,currentOrder);
								return;
						}else
							System.out.println("No se puede cobrar un pedido vacio. Complételo...");
					} catch (VoidOrderException e) {
						e.printStackTrace();
					}
				break;
			default: System.out.println("Seleccione una opcion valida...");
			}
		}while(!option.equalsIgnoreCase("R"));	
		return;
	}
	
	public static void payAndFinishOrder(ICafe currentCafe, Order currentOrder) throws Exception 
	{
		currentCafe.FinishOrder(currentOrder);
		
		return;
	}
	
	 /** 
     * Function to show the daily cash box.
	 * @param cb Defines the cashbox to show.
	 * 
	 * 
	 */
	 public static void viewCashBox(CashBox cb) 
	 {
		String option;
		do 
		{
			System.out.println("\n\n"); 

			System.out.println("Consulta de la caja de hoy");
			System.out.println("-------------------------------");
			System.out.println("Pedidos registrados: "+cb.getnOrders());
			System.out.println("Caja: "+cb.getTotal()+" euros\n");
			System.out.println("R. Volver a la pantalla anterior");
			System.out.println("--------------------------------------");
			System.out.println("Introduzca una opción:");
		
			option = sc.nextLine();
			

		}while(!option.equalsIgnoreCase("R"));
		
	 }
 }






















