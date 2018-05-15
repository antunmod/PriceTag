package antunmod.projects.pricetag.model;

import java.io.Serializable;

/*
 * BaseProduct class contains basic information that is sent from application. The data it contains relates to most 
 * of the columns in product_specific table so new product_specific data is added.
 * 
 * This class is contained as a private variable in multiple classes which determine which 
 * new information, apart from addition to product_specific, should be saved to database.
 * 
 */
public class BaseProduct implements Serializable{

	private String barcode;
	private Short userId;
	private Float price;
	private String description;
	private String photoURI;
	private Float size;
	private String sizeUnit;

	public BaseProduct() {}
	
	public BaseProduct(String barcode, Short userId, Float price, String description, String photoURI,
			Float size, String sizeUnit) {
		super();
		this.barcode = barcode;
		this.userId = userId;
		this.price = price;
		this.description = description;
		this.photoURI = photoURI;
		this.size = size;
		this.sizeUnit = sizeUnit;
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

	public Float getsize() {
		return size;
	}

	public Short getUserId() {
		return userId;
	}

	public Float getPrice() {
		return price;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	public void setSizeUnit(String sizeUnit) {
		this.sizeUnit = sizeUnit;
	}

	public void setUserId(Short userId) {
		this.userId = userId;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setPhotoURI(String photoURI) {
		this.photoURI = photoURI;
	}
}
