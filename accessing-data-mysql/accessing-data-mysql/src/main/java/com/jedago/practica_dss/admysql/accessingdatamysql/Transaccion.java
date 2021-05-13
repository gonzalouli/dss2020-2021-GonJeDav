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
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	private String concepto;
	@OneToOne(targetEntity = User.class)
	private User usuario;
	private LocalDateTime fecha;
	private BigDecimal price;
	private boolean confirmado;

	Transaccion(){super();}
	
	Transaccion(User user, String concepto, LocalDateTime fecha, BigDecimal pay){
		this.usuario= user;
		this.concepto= concepto;
		this.fecha = fecha;
		this.price = pay;
		this.confirmado = false;
	}

	  /**
	 *Returns is the transaction is confirmed
	 * @return the confirmation
	 */
	public boolean isConfirmado() {
		return confirmado;
	}

	  /**
		 *Set the transaction to confirmed or not confirmed.
		 * @param the value of the confirmation
		 */
	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}

	/**Return the id of the transaction
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**Return the concept of the transaction
	 * @return the concept
	 */
	public String getConcepto() {
		return concepto;
	}

	/**Insert a concept to an transaction.
	 * @param concepto the concept to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**Return the user of the transaction
	 * @return the user
	 */
	public User getUsuario() {
		return usuario;
	}

	/** Get the date of a transaction
	 * @return the date of the transaction
	 */
	public LocalDateTime getFecha() {
		return fecha;
	}

	/**Set the date of a transaction
	 * @param fecha the date to set
	 */
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	/** Get the price of a transaction
	 * @return the price of an transaction
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**Update the date to the actual date of an existing transaction
	 *
	 */
	public void updateDate() {
		this.fecha=LocalDateTime.now();
	}

}
