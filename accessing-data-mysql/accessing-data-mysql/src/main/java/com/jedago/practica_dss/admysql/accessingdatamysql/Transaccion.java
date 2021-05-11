package com.jedago.practica_dss.admysql.accessingdatamysql;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javassist.compiler.ast.Pair;
@Entity
public class Transaccion {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private User sender, receiver;
	private LocalDate fecha;
	private BigDecimal amount;
	
	Transaccion(User sender, User receiver, BigDecimal amount){
		this.sender= sender;
		this.receiver= receiver;
		this.amount = amount;
	}

	public User getSender() {
		return sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	

}
