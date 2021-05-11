package com.jedago.practica_dss.admysql.accessingdatamysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Retencion {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	@ManyToOne(targetEntity = Transaccion.class)
	private Transaccion transaccionRetenida;
	
	Retencion(){}
	
	Retencion(Transaccion t){
		this.transaccionRetenida =t;
	}
	
	public String getId() {
		return id;
	}

	public Transaccion getTransaccionRetenida() {
		return transaccionRetenida;
	}
	
	public void setTransaccionRetenida(Transaccion transaccionRetenida) {
		this.transaccionRetenida = transaccionRetenida;
	}
}
