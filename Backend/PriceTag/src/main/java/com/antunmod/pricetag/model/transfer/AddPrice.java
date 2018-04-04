package com.antunmod.pricetag.model.transfer;

/*
 * The AddPrice class contains data for saving a new price for an existing product in the given store.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- price
 */

public class AddPrice {

	private short productSpecificId;
	private short storeSpecificId;
	private short userId;
	private float price;
	private AddStoreSpecificProductStore a;

}
