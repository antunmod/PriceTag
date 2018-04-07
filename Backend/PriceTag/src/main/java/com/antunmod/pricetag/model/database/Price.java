package com.antunmod.pricetag.model.database;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class represents a price update of a specific product in a specific store.
 */
@Entity
@Table(name = "price")
public class Price implements Serializable {

	private static final long serialVersionUID = 1945715940307189952L;

	@Id
	@Column(name = "price_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer priceId;

	/*
	 * References a specific product in a specific store.
	 */
	@Column(name = "product_store_ID")
	private Short productStoreId;

	@Column(name = "user_ID")
	private Short userId;

	@Column(name = "price")
	private Float price;

	@Column(name = "price_change_date")
	private String productChangeDate;

	/*
	 * Validity specifies the validity of the price. It is used for calculating user
	 * rating. The price is valid (1) when added. When the new price is reported as
	 * invalid by another user and approved as invalid by an admin, its value is
	 * changed to invalid (0) and the product price is set to the last valid price.
	 */
	@Column(name = "validity")
	private byte validity;

	public Price() {}
	
	public Price(Short productStoreId, Short userId, Float price) {
		super();
		this.productStoreId = productStoreId;
		this.userId = userId;
		this.price = price;
		this.validity = 1;
		this.productChangeDate = getDateString();
	}

	public Short getProductStoreId() {
		return productStoreId;
	}
	
	private String getDateString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

}
