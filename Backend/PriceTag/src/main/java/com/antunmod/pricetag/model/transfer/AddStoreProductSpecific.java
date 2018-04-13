package com.antunmod.pricetag.model.transfer;

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

public class AddStoreProductSpecific {
	
	private BaseProduct baseProduct;
	private Short productId;
	private String storeName;
	private String storeAddress;
	
	public String getStoreName() {
		return storeName;
	}
	
	public AddStoreSpecificProductSpecific toAddStoreSpecificProductSpecific(Short storeId) {
		return new AddStoreSpecificProductSpecific(baseProduct, productId, storeId, storeAddress);
	}
}
