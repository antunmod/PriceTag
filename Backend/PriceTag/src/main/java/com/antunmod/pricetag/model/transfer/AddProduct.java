package com.antunmod.pricetag.model.transfer;


/*
 * The class AddProduct saves a new product to the given store.

 * Contains data which will be used to create the following new entries in database:
 * 		- product
 * 		- product_specific
 * 		- product_store
 * 		- price
 * 		- subcategory_product
 */
public class AddProduct {
	
	private BaseProduct baseProduct;
	private short producerId;
	private String productName;
	private short storeSpecificId;
	private short subcategoryId;
	
}
