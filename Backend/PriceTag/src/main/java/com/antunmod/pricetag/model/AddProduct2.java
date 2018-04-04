package com.antunmod.pricetag.model;

public class AddProduct2 {

	private String productName;
	private String producer;
	private byte[] photo;
	private int storeId;
	private int userId;
	private String barcode;
	private String productDescription;
	private float productSize;
	private int productSizeId;
	private float price;
	private String priceChangeDate;
	private int subcategoryId;

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

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
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

	public int getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryName(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

}
