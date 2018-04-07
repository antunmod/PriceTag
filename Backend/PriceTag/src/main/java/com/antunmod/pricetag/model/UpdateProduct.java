package com.antunmod.pricetag.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UpdateProduct implements Serializable{
	
	private static final long serialVersionUID = 3685849263625296026L;

	@Id
	@Column (name = "product_store_ID")
	private long productStoreId;
	
	@Column (name = "user_ID")
	private Integer userId;
	
	@Column (name = "photo_ID")
	private Integer photoId;
	
	@Column (name = "producer")
	private String producer;
	
	@Column (name = "product_name")
	private String name;
	
	@Column (name = "product_size")
	private Float size;
	
	@Column (name = "size_type")
    private String sizeType;
	
	@Column (name = "price")
    private Float price;
	
	@Column (name = "average_price")
    private Float averagePrice;
	
	@Column (name = "product_updates")
    private Integer productUpdates;
	
	@Column (name = "price_change_date")
    private String priceChangeDate;

	
	public UpdateProduct() {
		super();
	}

	public UpdateProduct(long productStoreId, Integer userId, Integer photoId, String producer, String name, Float size,
			String sizeType, Float price, Float averagePrice, Integer productUpdates, String priceChangeDate) {
		super();
		this.productStoreId = productStoreId;
		this.userId = userId;
		this.photoId = photoId;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
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

	public Float getSize() {
		return size;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	public String getSizeType() {
		return sizeType;
	}

	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(Float averagePrice) {
		this.averagePrice = averagePrice;
	}

	public Integer getProductUpdates() {
		return productUpdates;
	}

	public void setProductUpdates(Integer productUpdates) {
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
