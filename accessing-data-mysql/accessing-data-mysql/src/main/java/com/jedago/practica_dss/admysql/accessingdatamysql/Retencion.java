package com.jedago.practica_dss.admysql.accessingdatamysql;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Retencion {

	@Id
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	@OneToOne(targetEntity = Transaccion.class)
	private Transaccion transaccionRetenida;
	
	Retencion(){}
	
	Retencion(Transaccion t){
		this.transaccionRetenida =t;
		this.id = UUID.randomUUID().toString();
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
