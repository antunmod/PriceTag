package com.antunmod.pricetag.model.transfer;

import java.io.Serializable;

/*
 * The AddStoreProduct class contains data for saving a new product to the new store name at a new store address.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- store
 * 		- store_specific
 * 		- product_specific
 * 		- product_store
 * 		- price
 * 		- subcategory_product
 */

public class AddStoreProduct implements Serializable {

	private static final long serialVersionUID = 5055898653143162572L;

	private BaseProduct baseProduct;
	private Short producerId;
	private String productName;
	private String storeName;
	private String storeAddress;
	private Short subcategoryId;

	public AddStoreProduct() {
	}

	public BaseProduct getBaseProduct() {
		return baseProduct;
	}

	public Short getProducerId() {
		return producerId;
	}

	public String getProductName() {
		return productName;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public Short getSubcategoryId() {
		return subcategoryId;
	}

	public AddStoreSpecificProduct toAddStoreSpecificProduct(Short storeId) {
		return new AddStoreSpecificProduct(baseProduct, producerId, productName, storeId, storeAddress, subcategoryId);
	}
}
