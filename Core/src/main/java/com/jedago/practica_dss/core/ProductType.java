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
	
	public ProductType() {super();}

	public ProductType(int id, String TypeName) {
		this.id = id;
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
