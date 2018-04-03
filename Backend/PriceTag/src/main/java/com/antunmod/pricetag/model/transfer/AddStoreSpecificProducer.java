package com.antunmod.pricetag.model.transfer;

/*
 * The AddStoreSpecificProducer class contains data for saving a new product by a new producer 
 * to the new store address.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- store_specific
 * 		- producer
 * 		- product
 * 		- product_specific
 * 		- product_store
 * 		- price
 * 		- subcategory_product
 */

public class AddStoreSpecificProducer {

	private BaseProduct baseProduct;
	private String producerName;
	private String productName;
	private byte storeId;
	private String storeAddress;
	private short subcategoryId;
}
