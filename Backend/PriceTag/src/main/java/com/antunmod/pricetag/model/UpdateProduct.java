package com.antunmod.pricetag.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class UpdateProduct implements Serializable{
	
	private static final long serialVersionUID = 3685849263625296026L;

	@Id
	@Column (name = "product_store_ID")
	private long productStoreId;
	
	@Column (name = "user_ID")
	private int userId;
	
	@Lob
	@Column (name = "photo")
	private byte[] photo;
	
	@Column (name = "producer")
	private String producer;
	
	@Column (name = "product_name")
	private String name;
	
	@Column (name = "product_size")
	private int size;
	
	@Column (name = "size_type")
    private String sizeType;
	
	@Column (name = "price")
    private float price;
	
	@Column (name = "average_price")
    private float averagePrice;
	
	@Column (name = "product_updates")
    private int productUpdates;
	
	@Column (name = "price_change_date")
    private String priceChangeDate;

	
	public UpdateProduct() {
		super();
	}

	public UpdateProduct(long productStoreId, int userId, byte[] photo, String producer, String name, int size,
			String sizeType, float price, float averagePrice, int productUpdates, String priceChangeDate) {
		super();
		this.productStoreId = productStoreId;
		this.userId = userId;
		this.photo = photo;
		this.producer = producer;
		this.name = name;
		this.size = size;
		this.sizeType = sizeType;
		this.price = price;
		this.averagePrice = averagePrice;
		this.productUpdates = productUpdates;
		this.priceChangeDate = priceChangeDate;
	}

	public long getProductStoreId() {
		return productStoreId;
	}

	public void setProductStoreId(long productStoreId) {
		this.productStoreId = productStoreId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSizeType() {
		return sizeType;
	}

	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	public String getPriceChangeDate() {
		return priceChangeDate;
	}

	public void setPriceChangeDate(String priceChangeDate) {
		this.priceChangeDate = priceChangeDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
