package com.jedago.practica_dss.core;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Client {
	
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String dni;
	
	public Client(String firstName, String lastName, LocalDate birthDate, String dni) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.dni = dni;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public int getAge() {
		
		LocalDate now = LocalDate.now();
		Period period = Period.between(birthDate, now);
		return period.getYears();
		
	}
	
	

}
