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
	
	public static void main(String[] args) throws IOException 
	{		
		
		
	}
	
	public void mainScreen() throws IOException 
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
				case "1": 
					break;
				case "2": 
					break;
				case "Q":
					break;
				default: System.out.println("Introduzca una opción valida... ");
			}
			
		}while(option!="1" && option!="2" && option!="Q" );
	}
	
	
	public void currentOrder(Order order) throws IOException 
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
			case "1": addProduct();
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
	
	
	
	
	public void addProductType() throws IOException, ClassNotFoundException {
	
		try {
			
			ObjectInputStream getProductTypes = new ObjectInputStream(new FileInputStream("productType.txt"));
			
			List<ProductType> ListProductTypes = new ArrayList<ProductType>();
			
			ListProductTypes = ((List<ProductType>) getProductTypes.readObject());
			
			getProductTypes.close();
			
			for(ProductType pt: ListProductTypes) 
			{
				System.out.println(pt);
			}
			
		}catch(Exception e) {}
		
		
		
		
		
		
		
	}

	
	
	
	public void addProduct() throws IOException {
		
		try { //visualizacion de productos
		
			ObjectInputStream getProduct = new ObjectInputStream(new FileInputStream("product.txt"));
			
			List<Product> ListProduct =  new ArrayList<Product>();
			ListProduct = (List<Product>) getProduct.readObject();
			
			getProduct.close();
			
			for(Product p: ListProduct) 
			{
				System.out.println(p);
			}
			
		}catch(Exception e) {}
		
		
		
		
		
		
	}
	
	
	
	
	
	

	public void addOrderLine() throws IOException {
		
		try { //visualizacion de productos
		
			ObjectInputStream getOrderLine = new ObjectInputStream(new FileInputStream("orderLine.txt"));
			
			List<Product> ListOrderLine =  new ArrayList<Product>();
			ListOrderLine = (List<Product>) getOrderLine.readObject();
			
			getOrderLine.close();
			
			for(Product p: ListOrderLine) 
			{
				System.out.println(p);
			}
	
		}catch(Exception e) {}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
