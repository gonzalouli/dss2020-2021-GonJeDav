package com.jedago.practica_dss.cli;
import java.lang.*;
public class Pantalla implements IPantalla{

	@Override
	public  void cleanScr() {
		  try{
	            String operatingSystem = System.getProperty("os.name");//Check the current operating system
	          
	            if(operatingSystem.contains("Windows")){        
	                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

	            } else {
	                ProcessBuilder pb = new ProcessBuilder("clear");
	                Process startProcess = pb.start();

	                startProcess.waitFor();
	            } 
	        }catch(Exception e){
	            System.out.println(e);
	        }
	}
	public Pantalla() {}

}
