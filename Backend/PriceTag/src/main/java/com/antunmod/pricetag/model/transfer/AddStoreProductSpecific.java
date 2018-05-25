package com.antunmod.pricetag.model.transfer;

import java.io.Serializable;

/*
 * The AddStoreProductSpecific class contains data for saving a new product size to the new store name 
 * at a new store address.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- store
 * 		- store_specific
 * 		- product_specific
 * 		- product_store
 * 		- price
 */

public class AddStoreProductSpecific implements Serializable {

	private static final long serialVersionUID = -9143908378866914782L;

	private BaseProduct baseProduct;
	private Short productId;
	private String storeName;
	private String storeAddress;

	public AddStoreProductSpecific() {
	}

	public BaseProduct getBaseProduct() {
		return baseProduct;
	}

	public Short getProductId() {
		return productId;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public AddStoreSpecificProductSpecific toAddStoreSpecificProductSpecific(Short storeId) {
		return new AddStoreSpecificProductSpecific(baseProduct, productId, storeId, storeAddress);
	}
}
