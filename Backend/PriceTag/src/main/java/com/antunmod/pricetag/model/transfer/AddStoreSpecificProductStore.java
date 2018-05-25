package com.antunmod.pricetag.model.transfer;

import java.io.Serializable;

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

public class AddStoreSpecificProductStore implements Serializable{

	private static final long serialVersionUID = 536324506857401177L;
	
	private Short productSpecificId;
	private Short storeId;
	private String storeAddress;
	private Short userId;
	private Float price;

	public AddStoreSpecificProductStore() {
	}

	public AddStoreSpecificProductStore(Short storeId, String storeAddress, Short userId, Float price) {
		super();
		this.storeId = storeId;
		this.storeAddress = storeAddress;
		this.userId = userId;
		this.price = price;
	}

	public Short getProductSpecificId() {
		return productSpecificId;
	}

	public Short getStoreId() {
		return storeId;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public Short getUserId() {
		return userId;
	}

	public Float getPrice() {
		return price;
	}

	public ProductStore toProductStore(Short storeSpecificId) {
		return new ProductStore(productSpecificId, storeSpecificId);
	}

	public Price toPrice(Short productStoreId) {
		return new Price(productStoreId, userId, price);
	}

}
