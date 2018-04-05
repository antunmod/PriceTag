package com.antunmod.pricetag.model.transfer;

/*
 * BaseProduct class contains basic information that is sent from application. The data it contains relates to most 
 * of the columns in product_specific table so new product_specific data is added.
 * 
 * This class is contained as a private variable in multiple classes which determine which 
 * new information, apart from addition to product_specific, should be saved to database.
 * 
 */
public class BaseProduct {

	private String barcode;
	private short userId;
	private float price;
	private String productDescription;
	private String photoURI;
	private float productSize;
	private byte productSizeId;

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

	public String getPhotoURI() {
		return photoURI;
	}

	public float getProductSize() {
		return productSize;
	}

	public byte getProductSizeId() {
		return productSizeId;
	}

	public short getUserId() {
		return userId;
	}

	public float getPrice() {
		return price;
	}

}
