package com.jedago.practica_dss.core;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Jesús Serrano Gallán
 *	@version 1.0
 */
public class ProductType implements Serializable {

	private static final long serialVersionUID = -3544695254552823682L;
	/**
	 * 
	 */

	private String id;
	private String TypeName;
	
	public ProductType() {
		super();
		this.id = UUID.randomUUID().toString();
	}

	public ProductType(String TypeName) {
		this.TypeName = TypeName;
	}

	/**
	 * @return Return the id_.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return Return the typeName.
	 */
	public String getTypeName() {
		return TypeName;
	}
}
