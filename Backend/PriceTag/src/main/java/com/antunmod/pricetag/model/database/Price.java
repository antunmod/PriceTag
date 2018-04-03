package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "price")
public class Price implements Serializable{

	private static final long serialVersionUID = 1945715940307189952L;

	@Id
	@Column(name = "price_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int priceId;
	
	@Column(name = "product_store_ID")
	private int productStoreId;
	
	@Column (name = "user_ID")
	private int userId;
	
	@Column (name = "price")
	private float price;
	
	@Column (name = "price_change_date")
	private String productChangeDate;
	
	@Column (name = "validity")
	private byte validity;

	public Price() {
		super();
	}

	public Price(int priceId, int productStoreId, int userId, float price, String productChangeDate, byte validity) {
		super();
		this.priceId = priceId;
		this.productStoreId = productStoreId;
		this.userId = userId;
		this.price = price;
		this.productChangeDate = productChangeDate;
		this.validity = validity;
	}

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public int getProductStoreId() {
		return productStoreId;
	}

	public void setProductStoreId(int productStoreId) {
		this.productStoreId = productStoreId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getProductChangeDate() {
		return productChangeDate;
	}

	public void setProductChangeDate(String productChangeDate) {
		this.productChangeDate = productChangeDate;
	}

	public byte getValidity() {
		return validity;
	}

	public void setValidity(byte validity) {
		this.validity = validity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
