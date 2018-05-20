package antunmod.projects.pricetag.transfer;


/*
 * The AddPrice class contains data for saving a new price for an existing product in the given store.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- price
 */

import java.io.Serializable;

public class AddPrice implements Serializable{

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
	
}
