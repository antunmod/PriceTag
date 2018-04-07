package com.antunmod.pricetag.model;

public class AddProduct2 {

	private String productName;
	private String producer;
	private byte[] photo;
	private Integer storeId;
	private Integer userId;
	private String barcode;
	private String productDescription;
	private Float productSize;
	private Integer productSizeId;
	private Float price;
	private String priceChangeDate;
	private Integer subcategoryId;

	public AddProduct2() {
		super();
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Float getProductSize() {
		return productSize;
	}

	public void setProductSize(Float productSize) {
		this.productSize = productSize;
	}

	public Integer getProductSizeId() {
		return productSizeId;
	}

	public void setProductSizeId(Integer productSizeId) {
		this.productSizeId = productSizeId;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getPriceChangeDate() {
		return priceChangeDate;
	}

	public void setPriceChangeDate(String priceChangeDate) {
		this.priceChangeDate = priceChangeDate;
	}

	public Integer getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryName(Integer subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

}
