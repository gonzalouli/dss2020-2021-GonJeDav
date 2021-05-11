package com.jedago.practica_dss.admysql.accessingdatamysql;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private String id;
  private String firstName;
  private String lastName;
  private String email;
  private List<Transaccion> transacciones;
  private BigDecimal saldo;
  
  User(){super();}
  
  User(String firstName, String lastName, String email){
	  this.firstName = firstName;
	  this.lastName = lastName;
	  this.email = email;
	  transacciones = new ArrayList<Transaccion>();
	  saldo = BigDecimal.ZERO;
  }

  public String getId() {
    return id;
  }

  /**
 * @return the lastName
 */
public String getLastName() {
	return lastName;
}

/**
 * @param lastName the lastName to set
 */
public void setLastName(String lastName) {
	this.lastName = lastName;
}

/**
 * @return the saldo
 */
public BigDecimal getSaldo() {
	return saldo;
}

/**
 * @param saldo the saldo to set
 */
public void setSaldo(BigDecimal saldo) {
	this.saldo = saldo;
}

public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

public void ingreso(BigDecimal add) {
	this.saldo = this.saldo.add(add);
}

public void retiro(BigDecimal substract) {
	this.saldo = this.saldo.subtract(substract);
}

public void pago(User user, String concepto, BigDecimal amount) {
	if(getSaldo().compareTo(amount)>=0) {
		user.retiro(amount);
		LocalDateTime td = LocalDateTime.now();
		Transaccion t = new Transaccion(user, concepto, td, amount);
		this.transacciones.add(t);
	}
}



}
