package com.antunmod.pricetag.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="product_store")
public class ProductStore implements Serializable{

	private static final long serialVersionUID = 7515006165127257791L;

	@Id
	@Column (name = "product_store_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long productStoreId;
	
	@Column(name="product_ID")
	private int productId;
	
	@Column(name="store_ID")
	private int storeId;

	@Column(name="user_ID")
	private int userId;
	
	@Column(name="barcode")
	private String barcode;
	
	@Column (name="product_description")
	private String productDesctiption;
	
	@Column(name="photo_ID")
	private int photoId;
	
	@Column(name="product_size")
	private float productSize;
	
	@Column(name="product_size_ID")
	private int productSizeId;
	
	@Column(name="price")
	private float price;
	
	@Column(name="price_change_date")
	private String priceChangeDate;
	
	@Column(name="average_price")
	private float averagePrice;
	
	@Column(name="product_updates")
	private int productUpdates;

	
	
	public ProductStore() {
		super();
	}



	public ProductStore(long productStoreId, int productId, int storeId, int userId, String barcode,
			String productDesctiption, int photoId, float productSize, int productSizeId, float price,
			String priceChangeDate, float averagePrice, int productUpdates) {
		super();
		this.productStoreId = productStoreId;
		this.productId = productId;
		this.storeId = storeId;
		this.userId = userId;
		this.barcode = barcode;
		this.productDesctiption = productDesctiption;
		this.photoId = photoId;
		this.productSize = productSize;
		this.productSizeId = productSizeId;
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



	public int getProductId() {
		return productId;
	}



	public void setProductId(int productId) {
		this.productId = productId;
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



	public String getBarcode() {
		return barcode;
	}



	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}



	public String getProductDesctiption() {
		return productDesctiption;
	}



	public void setProductDesctiption(String productDesctiption) {
		this.productDesctiption = productDesctiption;
	}



	public int getPhotoId() {
		return photoId;
	}



	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}



	public float getProductSize() {
		return productSize;
	}



	public void setProductSize(float productSize) {
		this.productSize = productSize;
	}



	public int getProductSizeId() {
		return productSizeId;
	}



	public void setProductSizeId(int productSizeId) {
		this.productSizeId = productSizeId;
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

	

		
}
