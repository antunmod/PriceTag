package com.antunmod.pricetag.model.transfer;

import java.io.Serializable;

/*
 * The AddStoreProductStore class contains data for saving an existing product with a new price 
 * to the new store name at the new store address. 
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- store
 * 		- store_specific
 * 		- product_store
 * 		- price
 */

public class AddStoreProductStore implements Serializable{

	private static final long serialVersionUID = 5124758720404910421L;
	
	private Short productSpecificId;
	private String storeName;
	private String storeAddress;
	private Short userId;
	private Float price;

	public AddStoreProductStore() {
	}

	public Short getProductSpecificId() {
		return productSpecificId;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public Short getUserId() {
		return userId;
	}

	public Float getPrice() {
		return price;
	}

	public AddStoreSpecificProductStore toAddStoreSpecificProductStore(Short storeId) {
		return new AddStoreSpecificProductStore(productSpecificId, storeId, storeAddress, userId, price);
	}
}
