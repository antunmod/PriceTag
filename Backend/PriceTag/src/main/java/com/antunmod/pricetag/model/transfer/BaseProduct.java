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
	private Short userId;
	private Float price;
	private String description;
	private String photoURI;
	private Float size;
	private Byte sizeId;

	public BaseProduct() {}
	
	public BaseProduct(String barcode, Short userId, Float price, String description, String photoURI,
			Float size, Byte sizeId) {
		super();
		this.barcode = barcode;
		this.userId = userId;
		this.price = price;
		this.description = description;
		this.photoURI = photoURI;
		this.size = size;
		this.sizeId = sizeId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhotoURI() {
		return photoURI;
	}

	public Float getSize() {
		return size;
	}

	public byte getSizeId() {
		return sizeId;
	}

	public Short getUserId() {
		return userId;
	}

	public Float getPrice() {
		return price;
	}

}
