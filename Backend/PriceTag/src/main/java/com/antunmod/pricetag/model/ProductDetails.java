package com.antunmod.pricetag.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ProductDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column (name = "product_store_ID")
	private long productStoreId;
	
	@Column (name = "product_ID")
	private long productId;
	
	@Column (name = "subcategory_ID")
    private int subcategoryId;
	
	@Column (name = "category_ID")
    private int categoryId;
	
	@Column (name = "sector_ID")
    private int sectorId;

	@Column (name = "store_ID")
    private int storeId;
	
	@Column (name = "user_ID")
    private int userId;
	
	
	@Column (name = "product_name")
    private String name;
	
	@Column (name = "producer")
    private String producer;
	
	@Column (name = "barcode")
    private String barcode;
	
	@Column (name = "photo")
    private byte[] photo;
	
	@Column (name = "product_size")
    private int size;
	
	@Column (name = "size_type")
    private String sizeType;
	
	@Column (name = "price")
    private float price;
	
	@Column (name = "price_change_date")
    private String priceChangeDate;
	
	@Column (name = "average_price")
    private float averagePrice;
	
	@Column (name = "product_updates")
    private int productUpdates;
	
	public ProductDetails(long productId, int subcategoryId, int categoryId, int sectorId, int storeId, int userId,
			String name, String producer, String barcode, byte[] photo, int size, String sizeType, float price,
			String priceChangeDate, float averagePrice, int productUpdates) {
		super();
		this.productId = productId;
		this.subcategoryId = subcategoryId;
		this.categoryId = categoryId;
		this.sectorId = sectorId;
		this.storeId = storeId;
		this.userId = userId;
		this.name = name;
		this.producer = producer;
		this.barcode = barcode;
		this.photo = photo;
		this.size = size;
		this.sizeType = sizeType;
		this.price = price;
		this.priceChangeDate = priceChangeDate;
		this.averagePrice = averagePrice;
		this.productUpdates = productUpdates;
	}

	public ProductDetails() {}
	
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getSectorId() {
		return sectorId;
	}

	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
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
