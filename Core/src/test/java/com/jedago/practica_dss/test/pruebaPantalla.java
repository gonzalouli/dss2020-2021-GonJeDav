package com.jedago.practica_dss.test;

import java.util.Scanner;

public class pruebaPantalla {

	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Basura");
		System.out.println("Basura");
		System.out.println("Basura");
		System.out.println("Basura");
		System.out.println("Basura");
		System.out.println("Basura");
		
		System.out.println("Introduce una tecla para borrar");
		sc.nextLine();
		clearScreen(); //No se si funciona, tendría que probarlo en la terminal de windows
	}
	
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	} 

}
