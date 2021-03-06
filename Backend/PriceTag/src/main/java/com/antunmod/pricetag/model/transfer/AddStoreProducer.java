package com.antunmod.pricetag.model.transfer;

import java.io.Serializable;

/*
 * The AddStoreProducer class contains data for saving a new product by a new producer 
 * to the new store name at a new store address.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- store
 * 		- store_specific
 * 		- producer
 * 		- product
 * 		- product_specific
 * 		- product_store
 * 		- price
 * 		- subcategory_product
 */

public class AddStoreProducer implements Serializable {

	private static final long serialVersionUID = -2867046453122638231L;

	private BaseProduct baseProduct;
	private String producerName;
	private String productName;
	private String storeName;
	private String storeAddress;
	private Short subcategoryId;

	public AddStoreProducer() {
	}

	public AddStoreProducer(BaseProduct baseProduct, String producerName, String productName, String storeName,
			String storeAddress, Short subcategoryId) {
		super();
		this.baseProduct = baseProduct;
		this.producerName = producerName;
		this.productName = productName;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.subcategoryId = subcategoryId;
	}

	public BaseProduct getBaseProduct() {
		return baseProduct;
	}

	public String getProducerName() {
		return producerName;
	}

	public String getProductName() {
		return productName;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public Short getSubcategoryId() {
		return subcategoryId;
	}

	public AddStoreSpecificProducer toAddStoreSpecificProducer(Short storeId) {
		return new AddStoreSpecificProducer(baseProduct, producerName, productName, storeId, storeAddress,
				subcategoryId);
	}
}
