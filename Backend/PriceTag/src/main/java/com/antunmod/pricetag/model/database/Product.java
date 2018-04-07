package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class represents product names and their producers.
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = -2721891796273863721L;

	@Id
	@Column(name = "product_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short productId;

	/*
	 * References producer_ID in producer table
	 */
	@Column(name = "producer_ID")
	private Short producerId;

	@Column(name = "product_name")
	private String productName;

	public Product() {}
	
	public Product(Short producerId, String productName) {
		super();
		this.producerId = producerId;
		this.productName = productName;
	}

	public Short getProductId() {
		return productId;
	}
	
	public String getProductName() {
		return productName;
	}

}
