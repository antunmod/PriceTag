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
	private Short producerId;
	private String productName;
	private byte storeId;
	private String storeAddress;
	private Short subcategoryId;

	public AddStoreSpecificProduct(BaseProduct baseProduct, Short producerId, String productName, byte storeId,
			String storeAddress, Short subcategoryId) {
		super();
		this.baseProduct = baseProduct;
		this.producerId = producerId;
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

	public AddProduct toAddProduct(Short storeSpecificId) {
		return new AddProduct(baseProduct, producerId, productName, storeSpecificId, subcategoryId);
	}

}
