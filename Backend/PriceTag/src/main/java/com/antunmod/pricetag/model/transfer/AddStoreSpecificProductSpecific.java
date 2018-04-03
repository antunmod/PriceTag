package com.antunmod.pricetag.model.transfer;

/*
 * The AddStoreSpecificProductSpecific class contains data for saving a new product size to the new store address.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- store_specific
 * 		- product_specific
 * 		- product_store
 * 		- price
 */

public class AddStoreSpecificProductSpecific {
	
	private BaseProduct baseProduct;
	private short productId;
	private byte storeId;
	private String storeAddress;
	
}
