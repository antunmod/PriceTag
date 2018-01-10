package com.antunmod.pricetag.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subcategory_product")
public class SubcategoryProduct implements Serializable{

	private static final long serialVersionUID = -6823809475104904608L;

	@Id
	@Column (name = "subcategory_product_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int subcategoryProductId;
	
	@Column (name = "subcategory_ID")
	private int subcategoryId;
	
	@Column (name = "product_ID")
	private int productId;
	
	public SubcategoryProduct() {}

	public SubcategoryProduct(int subcategoryProductId, int subcategoryId, int productId) {
		super();
		this.subcategoryProductId = subcategoryProductId;
		this.subcategoryId = subcategoryId;
		this.productId = productId;
	}

	public int getSubcategoryProductId() {
		return subcategoryProductId;
	}

	public void setSubcategoryProductId(int subcategoryProductId) {
		this.subcategoryProductId = subcategoryProductId;
	}

	public int getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
