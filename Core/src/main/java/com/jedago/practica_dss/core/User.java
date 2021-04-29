package com.jedago.practica_dss.core;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
/**@author Gonzalo Ulibarri Garcia
 *@version 1.0
 */

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1854084262648629283L;
	public static long currentid_user = 1;
	private List<Order> orderList;
	private long id_user;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String dni;
	
	
	
	public User() {}
	
	/** 
	* Create an user;
	* @param firstName The first name of an user
	* @param lastName The last name of an user
	* @param birthDate The birthDate of an user
	* @param dni The dni of an user
	*/
	public User(String firstName, String lastName, LocalDate birthDate, String dni) {
		LocalDate now = LocalDate.now();
		Period period = Period.between(birthDate, now);
		
		assert(period.getYears()>=18);
		
		orderList = new ArrayList<Order>();
		this.id_user = LocalDateTime.now().hashCode();
		//this.id_user = User.currentid_user;
		//User.currentid_user++;
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
	}
	
	
	/** 
     * Return a list of products bind to an user.
     * @return the order list of the user.
	 */
	public List<Order> getOrders() 
	{
		return orderList;
	}
	
	/** 
     * Return the id of an user.
     * @return the id of the user.
	 */
	public long getIdUser() 
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

}
