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

	public AddStoreSpecificProduct(BaseProduct baseProduct, short producerId, String productName, byte storeId,
			String storeAddress, short subcategoryId) {
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

	public AddProduct toAddProduct(short storeSpecificId) {
		return new AddProduct(baseProduct, producerId, productName, storeSpecificId, subcategoryId);
	}

}
