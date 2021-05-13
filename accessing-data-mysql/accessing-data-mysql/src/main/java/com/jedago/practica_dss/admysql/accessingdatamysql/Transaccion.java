package com.jedago.practica_dss.admysql.accessingdatamysql;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class Transaccion {
	@Id
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	private String concepto;
	@OneToOne(targetEntity = User.class)
	private User usuario;
	private LocalDateTime fecha;
	private BigDecimal price;
	
	Transaccion(){super();}
	
	Transaccion(User user, String concepto, LocalDateTime fecha, BigDecimal pay){
		this.usuario= user;
		this.concepto= concepto;
		this.fecha = fecha;
		this.setPrice(pay);
		this.id = UUID.randomUUID().toString();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * @return the usuario
	 */
	public User getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the fecha
	 */
	public LocalDateTime getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	

}
