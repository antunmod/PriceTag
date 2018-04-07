package com.antunmod.pricetag.model.transfer;

import com.antunmod.pricetag.model.database.Price;

/*
 * The AddPrice class contains data for saving a new price for an existing product in the given store.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- price
 */

public class AddPrice {

	private Short productSpecificId;
	private Short storeSpecificId;
	private Short userId;
	private Float price;

	public Short getProductSpecificId() {
		return productSpecificId;
	}

	public Short getStoreSpecificId() {
		return storeSpecificId;
	}
	
	public Price toPrice(Short productStoreId) {
		return new Price(productStoreId, userId, price);
	}
}
