package com.jedago.practica_dss.core;

import java.io.Serializable;

/**
 * @author Jesús Serrano Gallán
 *	@version 1.0
 */
public class ProductType implements Serializable {
	private int id_;
	private String TypeName;
	private static int LastId = 0;

	public ProductType(String TypeName) {
		LastId++;
		this.id_ = LastId;
		this.TypeName = TypeName;
	}

	/**
	 * @return Return the id_.
	 */
	public int getId_() {
		return id_;
	}

	/**
	 * @return Return the typeName.
	 */
	public String getTypeName() {
		return TypeName;
	}
}
