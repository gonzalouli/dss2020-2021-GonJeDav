package com.jedago.practica_dss.cli;

import java.io.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.jedago.practica_dss.cli.*;
import com.jedago.practica_dss.core.*;
import com.jedago.practica_dss.core.exceptions.NoStockException;

public class Main   {
	
	private static Scanner sc = new Scanner(System.in);
	private static Scanner scCantidad = new Scanner(System.in);

	private static IPantalla screen = new Pantalla();

	//public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Recorrer los pedidos y guardarlos en una lista
		//Recorrer los productos y guardarlos en una lista
		//Con la lista de pedidos y de productos, generar el Cafe
		
		//Menus:
		/*
		 * 1- Pantalla principal: crear pedido, consultar caja o salir [Recoger opción]
		 * 2- (desde pantalla 1) Creando pedido: añadir producto, eliminar producto, finalizar pedido, volver a pantalla anterior [Recoger opción] 
		 * 		- ¿si vuelves a la pantalla principal dsp de crear pedido, debe borrarse el pedido enn curso o debe aparece opción de seguir con pedido en curso?
		 * 3- (desde pantalla 2, añadir producto) Tipos de productos: Listado de los tipos de productos (añadir a producto, atributo con el tipo de producto) y volver a pantalla anterior [Recoger opción] 
		 * 4- (desde pantalla 3, introducido tipo producto) Listado de productos del tipo seleccionado con el precio unitario y volver a pantalla anterior
		 * 5- (desde pantalla 2) Eliminar producto, muestra listado de productos que están en el pedido actual, volver a pantalla anterior
		 * 6- (desde pantalla 2) Finalizar pedido, muestra total a pagar, opción de pagar y finalizar pedido y volver a pantalla anterior
		 * 7- (desde pantalla 2) Consultar caja de hoy, mostrar número de pedidos registrados y dinero de la caja
		 * */
		
		//Cada vez que se termina una orden se guarde en fichero
		
	//}
	
	public static void main(String[] args) throws Exception 
	{		
		//Leer los productos del fichero y devolverlos como lista
		List<Product> ProductList =  new ArrayList<Product>();
		ProductList = readProducts();
		
		if(ProductList.isEmpty())
		{
			ProductList = FirstProducts.getFirstProducts();
			writeProducts(ProductList);
		}
		//Leer los pedidos
		
		List<Order> OrderList =  new ArrayList<Order>();
		//OrderList = readOrders();
		
		//Creamos el Cafe
		
		ICafe cafe = new Cafe(OrderList, ProductList);
		
		mainScreen(cafe);
		
		//Generar un loop que cuando se termine un pedido escriba los cambios

		
		//Guardar los productos y los pedidos del cafe
		writeProducts(cafe.getAvailableProducts());
		writeOrders(cafe.getRegisteredOrders());
	}
	
	
	
//Operaciones de Serializacion con los productos	
	public static List<Product> readProducts() throws Exception
	{
		ObjectInputStream readProducts;
		List<Product> ProductList =  new ArrayList<Product>();
		try {
			readProducts = new ObjectInputStream(new FileInputStream(Messages.getString("ProductsFile")));
			ProductList = (List<Product>) readProducts.readObject();
			readProducts.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ProductList;
	}
	

	public static void writeProducts(List<Product> ProductList) throws Exception
	{
		ObjectOutputStream writeProducts;
		try {
			writeProducts = new ObjectOutputStream(new FileOutputStream(Messages.getString("ProductsFile")));
			writeProducts.writeObject(ProductList);
			writeProducts.flush();
			writeProducts.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
//Operaciones de Serializacion con los pedidos
	
	public static List<Order> readOrders() throws Exception
	{
		ObjectInputStream readOrders;
		List<Order> OrderList =  new ArrayList<Order>();
		try {
			readOrders = new ObjectInputStream(new FileInputStream(Messages.getString("OrdersFile")));
			OrderList = (List<Order>) readOrders.readObject();
			readOrders.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return OrderList;
	}
	
	public static void writeOrders(List<Order> OrderList) throws Exception
	{
		ObjectOutputStream writeOrders;
		try {
			writeOrders = new ObjectOutputStream(new FileOutputStream(Messages.getString("OrdersFile")));
			writeOrders.writeObject(OrderList);
			writeOrders.flush();
			writeOrders.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	 /** 
     * Show main screen with a CLI interface.
	 * @param currentCafe Defines the interface ICafe to operate with,
	 */
	public static void mainScreen(ICafe currentCafe) throws IOException 

	{	

		String option;
		do 
		{
			System.out.println("\n\n"); 

			System.out.println("Software de Cafeteria UCA"); //$NON-NLS-1$
			System.out.println("--------------------------------------"); //$NON-NLS-1$
			System.out.println("1. Crear pedido"); //$NON-NLS-1$
			System.out.println("2. Consultar Caja de hoy"); //$NON-NLS-1$
			System.out.println("Q. Salir"); //$NON-NLS-1$
			System.out.println("--------------------------------------"); //$NON-NLS-1$
			System.out.println("Introduzca una opción:"); //$NON-NLS-1$
			option = sc.nextLine();
			
			switch(option) 
			{

				case "1":	Order newOrder = currentCafe.newOrder();
						 	currentOrder(currentCafe, newOrder);
					break;
				case "2": 	viewCashBox(currentCafe.getTodayCashBox());
					break;
				case "Q": 
					break;
				default: System.out.println("Introduzca una opción valida... "); //$NON-NLS-1$
			}
			
		}while(!option.equals("Q"));
		
	}
	
	 /** 
     * Operations that can place an order 
	 * @param currentCafe Defines the interface ICafe to operate with.
	 * @param currentOrder Defines the current order opened
	 */
	public static void currentOrder(ICafe currentCafe, Order currentOrder) throws IOException 
	{	
		
		String option; 
		do 
		{
			System.out.println("\n\n"); 

			System.out.println("Pedido en curso ("+currentOrder.getId_order()+")"); //$NON-NLS-1$ //$NON-NLS-2$
			System.out.println("--------------------------------------"); //$NON-NLS-1$
			System.out.println("1. Añadir producto"); //$NON-NLS-1$
			System.out.println("2. Eliminar producto"); //$NON-NLS-1$
			System.out.println("3. Finalizar pedido"); //$NON-NLS-1$
			System.out.println("....."); //$NON-NLS-1$
			System.out.println("R. Volver a la pantalla anterior"); //$NON-NLS-1$
			System.out.println("--------------------------------------"); //$NON-NLS-1$
			System.out.println("Introduzca una opción:"); //$NON-NLS-1$
			
			option = sc.nextLine();

			switch(option) 
			{
			case "1":  selectProductType(currentCafe, currentOrder);
				break;
			case "2":  deleteProductFromCurrentOrder(currentCafe, currentOrder);
				break;
			case "3": //$NON-NLS-1$
				break;
			case "R":
				break;
			default: System.out.println("Introduzca una opción valida"); //$NON-NLS-1$
			}
			
		}while(!option.equals("R") );	 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		
	}


	 /** 
     * Select the type of the product to show. 
	 * @param currentCafe Defines the interface ICafe to operate with.
	 * @param currentOrder Defines the current order opened
	 */

		
	public static void selectProductType(ICafe currentCafe, Order currentOrder) {
		
		int convertToInt = 0;
		String option;
		int index;
		List<ProductType> productTypeList = currentCafe.getAvailableProductTypes();
		
		do {
			index=1;
			System.out.println("\n\n"); 

			System.out.println("Tipo de productos");
			System.out.println("----------------------------------");
			
			for(ProductType iter: productTypeList) {
				System.out.println(index+". "+iter.getTypeName());
				++index;
			}
			System.out.println("......");
			System.out.println("R. Volver a la pantalla anterior");
			System.out.println("--------------------------------------"); //$NON-NLS-1$
			System.out.println("Introduzca una opción:"); //$NON-NLS-1$
		
			
			option = sc.nextLine();
		

			if( !option.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ!@#$%^&*()_+}{<>;`~']") ) {
				
				convertToInt = Integer.parseInt(option);
			
				if(convertToInt>0 && convertToInt<=index) {
					giveProduct( currentCafe,  currentOrder, productTypeList.get(convertToInt-1));
				
				}
			else
				if( !option.equals("R"))
					System.out.println("Eleccion invalida, pruebe otra vez...");
				
			}
			
			

		}while( !option.equals("R") );
		
	}
		
	
	 /** 
     * Operations that is used to select a product. 
	 * @param currentCafe Defines the interface ICafe to operate with.
	 * @param currentOrder Defines the current order opened
	 * @param productType Defines the type of the product showed.
	 */
	
	public static void giveProduct(ICafe currentCafe, Order currentOrder, ProductType productType) 
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
			System.out.println("Tipo de productos");
			
			for(Product iter : productGiven) 
			{
				System.out.println(index+". "+iter.getName()+" ("+iter.getPriceUnit()+" euros)");
				++index;
			}
			
			System.out.println("......");
			System.out.println("R. Volver a la pantalla anterior");
			System.out.println("--------------------------------------"); 
			System.out.println("Introduzca una opción:"); 
			option = sc.nextLine();

			if( !option.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ!@#$%^&*()_+}{<>;`~']") ) {
				
				convertToInt = Integer.parseInt(option);
			
				if(convertToInt>0 && convertToInt<=index) {
					try {
							do{ 
								System.out.println("Introduzca la cantidad:"); 
								cant = scCantidad.nextInt();
						 	
							}while(cant<0);
						
						currentCafe.addProductToOrder(currentOrder, productGiven.get(convertToInt-1), cant);
					} catch (NoStockException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else
					if( !option.equals("R"))
						System.out.println("Eleccion invalida, pruebe otra vez...");
				
			

			
		}while( !option.equals("R"));
		

			
	}
	
	
	
	
	

	 /** 
     * Function to show the daily cash box.
	 * @param currentCafe Defines the interface ICafe to operate with.
	 * @param currentOrder Defines the current order opened
	 * @param productType Defines the type of the product showed.
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
			

		}while(!option.equals("R"));
		
	 }




 }






















