package com.antunmod.pricetag.model.transfer;

import com.antunmod.pricetag.model.database.Price;
import com.antunmod.pricetag.model.database.ProductStore;

/*
 * The AddStoreSpecificProductStore class contains data for saving an existing product with a new price 
 * to the new store address. 
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- store_specific
 * 		- product_store
 * 		- price
 */

public class AddStoreSpecificProductStore {

	private short productSpecificId;
	private byte storeId;
	private String storeAddress;
	private short userId;
	private float price;

	public AddStoreSpecificProductStore(byte storeId, String storeAddress, short userId, float price) {
		super();
		this.storeId = storeId;
		this.storeAddress = storeAddress;
		this.userId = userId;
		this.price = price;
	}

	public byte getStoreId() {
		return storeId;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public ProductStore toProductStore(short storeSpecificId) {
		return new ProductStore(productSpecificId, storeSpecificId);
	}

	public Price toPrice(short productStoreId) {
		return new Price(productStoreId, userId, price);
	}

}
