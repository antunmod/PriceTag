package com.antunmod.pricetag.model.transfer;

import java.io.Serializable;

/*
 * Used when finding product for existing barcode upon product update.
 */
public class ProductInformation implements Serializable {

	private static final long serialVersionUID = 2060294377562029420L;

	private Short productSpecificId;
	private String photoURI;
	private String productDescription;

	public ProductInformation(Short productSpecificId, String photoURI, String productDescription) {
		super();
		this.productSpecificId = productSpecificId;
		this.photoURI = photoURI;
		this.productDescription = productDescription;
	}

	public Short getProductSpecificId() {
		return productSpecificId;
	}
	
	public String getPhotoURI() {
		return photoURI;
	}

	public String getProductDescription() {
		return productDescription;
	}

}
