package com.antunmod.pricetag.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_specific")
public class ProductSpecific implements Serializable {

	private static final long serialVersionUID = -9196423082009556864L;

	@Id
	@Column(name = "product_specific_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productSpecificId;

	@Column(name = "product_ID")
	private int productId;

	@Column(name = "barcode")
	private String barcode;

	@Column(name = "product_description")
	private String productDesctiption;

	@Column(name = "photo_ID")
	private int photoId;

	@Column(name = "product_size")
	private float productSize;

	@Column(name = "product_size_ID")
	private int productSizeId;
	
	public ProductSpecific() {
		super();
	}

	public ProductSpecific(int productSpecificId, int productId, String barcode, String productDesctiption, int photoId,
			float productSize, int productSizeId) {
		super();
		this.productSpecificId = productSpecificId;
		this.productId = productId;
		this.barcode = barcode;
		this.productDesctiption = productDesctiption;
		this.photoId = photoId;
		this.productSize = productSize;
		this.productSizeId = productSizeId;
	}

	public int getProductSpecificId() {
		return productSpecificId;
	}

	public void setProductSpecificId(int productSpecificId) {
		this.productSpecificId = productSpecificId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
