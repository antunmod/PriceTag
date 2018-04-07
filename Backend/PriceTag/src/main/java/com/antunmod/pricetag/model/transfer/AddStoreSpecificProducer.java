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
	private Short subcategoryId;

	public AddStoreSpecificProducer(BaseProduct baseProduct, String producerName, String productName, byte storeId,
			String storeAddress, Short subcategoryId) {
		super();
		this.baseProduct = baseProduct;
		this.producerName = producerName;
		this.productName = productName;
		this.storeId = storeId;
		this.storeAddress = storeAddress;
		this.subcategoryId = subcategoryId;
	}

	public byte getStoreId() {
		return storeId;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public AddProducer toAddProducer(Short storeSpecificId) {
		return new AddProducer(baseProduct, producerName, productName, storeSpecificId, subcategoryId);
	}
}
