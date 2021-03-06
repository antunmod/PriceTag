package antunmod.projects.pricetag.transfer;

/*
 * The AddStoreSpecificProductStore class contains data for saving an existing product with a new price 
 * to the new store address. 
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- store_specific
 * 		- product_store
 * 		- price
 */

import java.io.Serializable;

public class AddStoreSpecificProductStore implements Serializable{

	private Short productSpecificId;
	private Short storeId;
	private String storeAddress;
	private Short userId;
	private Float price;

	public AddStoreSpecificProductStore(Short productSpecificId, Short storeId, String storeAddress, Short userId, Float price) {
		super();
		this.productSpecificId = productSpecificId;
		this.storeId = storeId;
		this.storeAddress = storeAddress;
		this.userId = userId;
		this.price = price;
	}

	public Short getStoreId() {
		return storeId;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

}
