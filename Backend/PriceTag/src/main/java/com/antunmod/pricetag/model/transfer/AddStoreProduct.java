package com.antunmod.pricetag.model.transfer;

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

public class AddStoreProduct {

	private BaseProduct baseProduct;
	private short producerId;
	private String productName;
	private String storeName;
	private String storeAddress;
	private short subcategoryId;
}