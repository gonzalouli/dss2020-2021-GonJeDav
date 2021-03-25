package com.jedago.practica_dss.core;

/**
 * @author Jesús Serrano Gallán
 *	@version 1.0
 */
public class ProductType {
	private int id_;
	private String TypeName;
	private static int LastId = 0;

	public ProductType(String TypeName) {
		LastId++;
		this.id_ = LastId;
		this.TypeName = TypeName;
	}

	/**
	 * @return the id_
	 */
	public int getId_() {
		return id_;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return TypeName;
	}
}
