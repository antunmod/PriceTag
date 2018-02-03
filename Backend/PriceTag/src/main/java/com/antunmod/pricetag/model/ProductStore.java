package com.antunmod.pricetag.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_store")
public class ProductStore implements Serializable {

	private static final long serialVersionUID = 7515006165127257791L;

	@Id
	@Column(name = "product_store_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productStoreId;

	@Column(name = "product_specific_ID")
	private int productSpecificId;

	@Column(name = "store_ID")
	private int storeId;

	@Column(name = "user_ID")
	private int userId;

	@Column(name = "price")
	private float price;

	@Column(name = "price_change_date")
	private String priceChangeDate;

	@Column(name = "average_price")
	private float averagePrice;

	@Column(name = "product_updates")
	private int productUpdates;

	public ProductStore() {
		super();
	}

	public ProductStore(long productStoreId, int productSpecificId, int storeId, int userId, float price,
			String priceChangeDate, float averagePrice, int productUpdates) {
		super();
		this.productStoreId = productStoreId;
		this.productSpecificId = productSpecificId;
		this.storeId = storeId;
		this.userId = userId;
		this.price = price;
		this.priceChangeDate = priceChangeDate;
		this.averagePrice = averagePrice;
		this.productUpdates = productUpdates;
	}

	public long getProductStoreId() {
		return productStoreId;
	}

	public void setProductStoreId(long productStoreId) {
		this.productStoreId = productStoreId;
	}

	public int getProductSpecificId() {
		return productSpecificId;
	}

	public void setProductSpecificId(int productSpecificId) {
		this.productSpecificId = productSpecificId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
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

	public String getPriceChangeDate() {
		return priceChangeDate;
	}

	public void setPriceChangeDate(String priceChangeDate) {
		this.priceChangeDate = priceChangeDate;
	}

	public float getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(float averagePrice) {
		this.averagePrice = averagePrice;
	}

	public int getProductUpdates() {
		return productUpdates;
	}

	public void setProductUpdates(int productUpdates) {
		this.productUpdates = productUpdates;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
