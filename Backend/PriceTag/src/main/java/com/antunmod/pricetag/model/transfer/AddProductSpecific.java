package com.antunmod.pricetag.model.transfer;

/*
 * The class AddProductSpecific saves a new size value for a product in a given store.

 * Contains data which will be used to create the following new entries in database:
 * 		- product_specific
 * 		- product_store
 * 		- price
 */

public class AddProductSpecific {

	private BaseProduct baseProduct;
	private short productId;
	private short storeSpecificId;

}
