package com.antunmod.pricetag.model.transfer;

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

public class AddStoreProductStore {

	private Short productSpecificId;
	private String storeName;
	private String storeAddress;
	private Short userId;
	private Float price;
	
	
	public String getStoreName() {
		return storeName;
	}
	
	public AddStoreSpecificProductStore toAddStoreSpecificProductStore(Short storeId) {
		return new AddStoreSpecificProductStore(storeId, storeAddress, userId, price);
	}
}
