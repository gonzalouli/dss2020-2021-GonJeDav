package com.jedago.practica_dss.admysql.accessingdatamysql;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // This tells Hibernate to make a table out of this class
public class User {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	  private String id;
	  private String firstName;
	  private String lastName;
	  private String email;
	  @OneToMany(targetEntity = Transaccion.class, cascade = CascadeType.ALL)
	  private List<Transaccion> transacciones;
	  private BigDecimal saldo;

	User(){
		  transacciones = new ArrayList<Transaccion>();
		  saldo = BigDecimal.ZERO;
	  }
	  
	  User(String firstName, String lastName, String email){
		  this.firstName = firstName;
		  this.lastName = lastName;
		  this.email = email;
		  transacciones = new ArrayList<Transaccion>();
		  saldo = BigDecimal.ZERO;
	  }
	
	  /**
	 *Returns the id of the user
	 * @return the id
	 */
	  public String getId() {
	    return id;
	  }
	
	  /**
	 *Returns the last name of the user
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 *insert the last name of the user

	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/** Return the balance of the user
	 * @return the saldo
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	
	/** Return the first name of the user
	 * @return the first name
	 */
	  public String getFirstName() {
	    return firstName;
	  }
	
		/**
		 *insert the first name of the user
		 * @param firstName the first name to set
		 */
	  public void setFirstName(String firstName) {
	    this.firstName = firstName;
	  }
	
	  /** Return the email of the user
		 * @return the email
		 */
	  public String getEmail() {
	    return email;
	  }
	
		/**
		 *insert the email of the user

		 * @param email the email to set
		 */
	  public void setEmail(String email) {
	    this.email = email;
	  }
	
	  /**
	 *Add money to the balance of the user
	 * @param add money to add
	 */
	public void ingreso(BigDecimal add) {
		this.saldo = this.saldo.add(add);
	}
	
	  /**
		 *Withdraw money to the balance of the user
		 * @param substract money to withdraw
		 */
	public void retiro(BigDecimal substract) {
		this.saldo = this.saldo.subtract(substract);
	}
	
	 /**
	 *Add a new transaction to the user
	 * @param t new transaction to add
	 */
	public void addTransaccion(Transaccion t)
	{
		this.transacciones.add(t);
	}

	  /**
	 *The user pay someone withdrawing money to his/her balance
	 * @param amount money to withdraw
	 */
	public void pago(BigDecimal amount) {
		if(getSaldo().compareTo(amount)>=0) {
			retiro(amount);
		}
	}
	 
	 /**
	 *Return a list of transactions owned by the user
	 * @return List of transactions
	 */
	@JsonIgnore
	 public List<Transaccion> getTransacciones() {
		return transacciones;
	}

}