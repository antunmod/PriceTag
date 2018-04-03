package com.antunmod.pricetag.model.transfer;

/*
 * The AddProducerProduct class contains data for saving a new product by a new producer to the given store.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- producer
 * 		- product
 * 		- product_specific
 * 		- product_store
 * 		- price
 * 		- subcategory_product
 */

public class AddProducer {
	
	private BaseProduct baseProduct;
	private String producerName;
	private String productName;
	private short storeSpecificId;
	private short subcategoryId;
}
