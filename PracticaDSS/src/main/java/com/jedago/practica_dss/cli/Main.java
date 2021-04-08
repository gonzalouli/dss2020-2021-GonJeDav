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
		//List<Order> OrderList =  new ArrayList<Order>();
		//OrderList = readOrders();
		//Creamos el Cafe
		//ICafe cafe = new Cafe(OrderList, ProductList);
		
		
		//Guardar los productos y los pedidos del cafe
		writeProducts(ProductList);
		//writeOrders(cafe.getRegisteredOrders());
	}
	
	public static List<Product> readProducts() throws Exception
	{
		ObjectInputStream readProducts;
		List<Product> ProductList =  new ArrayList<Product>();
		try {
			readProducts = new ObjectInputStream(new FileInputStream(Messages.getString("Main.1")));
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
			writeProducts = new ObjectOutputStream(new FileOutputStream(Messages.getString("Main.1")));
			writeProducts.writeObject(ProductList);
			writeProducts.flush();
			writeProducts.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Order> readOrders() throws Exception
	{
		ObjectInputStream readOrders;
		List<Order> OrderList =  new ArrayList<Order>();
		try {
			readOrders = new ObjectInputStream(new FileInputStream("orders.txt")); //$NON-NLS-1$
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
			writeOrders = new ObjectOutputStream(new FileOutputStream("orders.txt")); //$NON-NLS-1$
			writeOrders.writeObject(OrderList);
			writeOrders.flush();
			writeOrders.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mainScreen() throws IOException 
	{	
		String option; 
		do 
		{
			
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
				case "1":  //$NON-NLS-1$
					break;
				case "2":  //$NON-NLS-1$
					break;
				case "Q": //$NON-NLS-1$
					break;
				default: System.out.println("Introduzca una opción valida... "); //$NON-NLS-1$
			}
			
		}while(option!="1" && option!="2" && option!="Q" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
	
	
	public void currentOrder(Order order) throws IOException 
	{	
		String option; 

		do 
		{
			System.out.println("Pedido en curso ("+order.getId_order()+")"); //$NON-NLS-1$ //$NON-NLS-2$
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
			case "1":  //$NON-NLS-1$
				break;
			case "2":  //$NON-NLS-1$
				break;
			case "3": //$NON-NLS-1$
				break;
			case "R": //$NON-NLS-1$
				break;
			default: System.out.println("Introduzca una opción valida"); //$NON-NLS-1$
			}
			
		}while( option!="1" &&  option!="2" && option!="3" && option!="R" );	 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

}
