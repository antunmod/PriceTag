package com.antunmod.pricetag.model.transfer;

/*
 * The AddStoreSpecificProduct class contains data for saving a new product to the new store address.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- store_specific
 * 		- product
 * 		- product_specific
 * 		- product_store
 * 		- price
 * 		- subcategory_product
 */

public class AddStoreSpecificProduct {

	private BaseProduct baseProduct;
	private short producerId;
	private String productName;
	private byte storeId;
	private String storeAddress;
	private short subcategoryId;
}
