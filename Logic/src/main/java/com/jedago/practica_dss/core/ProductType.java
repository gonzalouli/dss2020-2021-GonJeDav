package com.jedago.practica_dss.core;

import java.io.Serializable;

/**
 * @author Jesús Serrano Gallán
 *	@version 1.0
 */
public class ProductType implements Serializable {

	private static final long serialVersionUID = -3544695254552823682L;
	/**
	 * 
	 */

	private int id;
	private String TypeName;
	private static int LastId = 0;

	public ProductType(String TypeName) {
		LastId++;
		this.id = LastId;
		this.TypeName = TypeName;
	}

	/**
	 * @return Return the id_.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return Return the typeName.
	 */
	public String getTypeName() {
		return TypeName;
	}
}
