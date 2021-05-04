package com.jedago.practica_dss.core;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
/**@author Gonzalo Ulibarri Garcia
 *@version 1.0
 */

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1854084262648629283L;
	private final String id_user;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String dni;
	@JsonIgnore
	private List<Order> orderList;
	
	public User() {
		this.id_user = UUID.randomUUID().toString();
		orderList = new ArrayList<Order>();
	}
	
	/** 
	* Create an user;
	* @param firstName The first name of an user
	* @param lastName The last name of an user
	* @param birthDate The birthDate of an user
	* @param dni The dni of an user
	*/
	public User( String firstName, String lastName, LocalDate birthDate, String dni) {
		LocalDate now = LocalDate.now();
		Period period = Period.between(birthDate, now);
		
		assert(period.getYears()>=18);
		this.id_user = UUID.randomUUID().toString();
		orderList = new ArrayList<Order>();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.dni = dni;
	}
	
	/** 
     * Bind a new order to an existing user.
     * @param the new order to this user.
	 */
	public void setOrder(Order o) 
	{
		orderList.add(o);
		System.out.println("Los pedidos del user son:");
		for(Order or: this.orderList)
		{
			System.out.println(or.getId_order());
		}
	}
	
	
	/** 
     * Return a list of products bind to an user.
     * @return the order list of the user.
	 */
	@JsonIgnore
	public List<Order> getOrders() 
	{
		return orderList;
	}
	
	/** 
     * Return the id of an user.
     * @return the id of the user.
	 */
	public String getIdUser() 
	{
		return this.id_user;
	}
	
	/** 
     * Return the first name of an user.
     * @return the first name of the user.
	 */
	public String getFirstName() {
		return firstName;
	}

	/** 
     * Set the first name of an existing user.
     * @param firstName the first name of the user.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** 
     * Return the last name of an user
     * @return the last name of the user
	 */
	public String getLastName() {
		return lastName;
	}

	/** 
     * Set the last name of an existing user.
     * @param lastName the first name of the user.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** 
     * Return the birth date of an user.
     * @return the birth date of the user.
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}

	/** 
     * Set the birth date of an existing user.
     * @param birthDate the birth date of the user.
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	/** 
     * Return the dni of an user.
     * @return the dni of the user.
	 */
	public String getDni() {
		return dni;
	}

	/** 
     * Set the dni of an existing user.
     * @param dni the dni date of the user.
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	/** 
     * Return the age of an user.
     * @return the age of the user.
	 */
	public int getAge() {
		
		LocalDate now = LocalDate.now();
		Period period = Period.between(birthDate, now);
		return period.getYears();
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_user == null) ? 0 : id_user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id_user == null) {
			if (other.id_user != null)
				return false;
		} else if (!id_user.equals(other.id_user))
			return false;
		return true;
	}
}
