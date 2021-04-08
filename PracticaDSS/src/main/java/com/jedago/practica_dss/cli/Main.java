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

public class Main   {
	
	private static Scanner sc = new Scanner(System.in);
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
		//ProductList = readProducts();
		Product p = new Product("patata", 2, new BigDecimal(3), new ProductType("Rico"));
		ProductList.add(p);
		//Leer los pedidos
		List<Order> OrderList =  new ArrayList<Order>();
		OrderList = readOrders();
		//Creamos el Cafe
		ICafe cafe = new Cafe(OrderList, ProductList);
		
		mainScreen(cafe);
		
		
		
		//Guardar los productos y los pedidos del cafe
		writeProducts(ProductList);
		writeOrders(cafe.getRegisteredOrders());
	}
	
//Operaciones de Serializacion con los productos	
	public static List<Product> readProducts() throws Exception
	{
		ObjectInputStream readProducts;
		List<Product> ProductList =  new ArrayList<Product>();
		try {
			readProducts = new ObjectInputStream(new FileInputStream("serializable/products.txt"));
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
			writeProducts = new ObjectOutputStream(new FileOutputStream("serializable/products.txt"));
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
			readOrders = new ObjectInputStream(new FileInputStream("orders.txt"));
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
			writeOrders = new ObjectOutputStream(new FileOutputStream("orders.txt"));
			writeOrders.writeObject(OrderList);
			writeOrders.flush();
			writeOrders.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
//Pantalla principal
	public static void mainScreen(ICafe currentCafe) throws IOException 
	{	
		String option; 
		do 
		{
			
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
				case "1":	Order newOrder = currentCafe.newOrder();
						 	currentOrder(newOrder);
					break;
				case "2": 	viewCashBox(currentCafe.getTodayCashBox());
					break;
				case "Q":
					break;
				default: System.out.println("Introduzca una opción valida... ");
			}
			
		}while(option!="1" && option!="2" && option!="Q" );
	}
	
//Pantalla de una nueva orden
	public static void currentOrder(Order order) throws IOException 
	{	
		
		
		String option; 
		do 
		{
			System.out.println("Pedido en curso ("+order.getId_order()+")");
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
			case "1": 
				break;
			case "2": 
				break;
			case "3":
				break;
			case "R":
				break;
			default: System.out.println("Introduzca una opción valida");
			}
			
		}while( option!="1" &&  option!="2" && option!="3" && option!="R" );	
	}



	/* 
	 * Visualizacion de cashbox.
	 * */
	
	 public static void viewCashBox(CashBox cb) 
	 {
		String option;

		System.out.println("Consulta de la caja de hoy");
		System.out.println("-------------------------------");
		System.out.println("Pedidos registrados: "+cb.getnOrders());
		System.out.println("Caja: "+cb.getTotal()+" euros\n");
		System.out.println("R. Volver a la pantalla anterior");
		System.out.println("--------------------------------------");
		System.out.println("Introduzca una opción:");
		
		do 
		{
		 option = sc.nextLine();
		
		}while(option != "R");
		
	 }







 }






















