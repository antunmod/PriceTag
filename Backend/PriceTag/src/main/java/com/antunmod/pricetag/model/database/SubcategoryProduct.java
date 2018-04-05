package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class represents a product in a subcategory.
 */
@Entity
@Table(name = "subcategory_product")
public class SubcategoryProduct implements Serializable {

	private static final long serialVersionUID = -6823809475104904608L;

	@Id
	@Column(name = "subcategory_product_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short subcategoryProductId;

	/*
	 * References a subcategory.
	 */
	@Column(name = "subcategory_ID")
	private short subcategoryId;

	/*
	 * References a product by a producer.
	 */
	@Column(name = "product_ID")
	private short productId;

	public SubcategoryProduct(short subcategoryId, short productId) {
		this.subcategoryId = subcategoryId;
		this.productId = productId;
	}

	
}
