package antunmod.projects.pricetag.transfer;

/*
 * The AddStoreProductSpecific class contains data for saving a new product size to the new store name 
 * at a new store address.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- store
 * 		- store_specific
 * 		- product_specific
 * 		- product_store
 * 		- price
 */

import java.io.Serializable;

import antunmod.projects.pricetag.model.BaseProduct;

public class AddStoreProductSpecific implements Serializable{
	
	private BaseProduct baseProduct;
	private Short productId;
	private String storeName;
	private String storeAddress;

	public AddStoreProductSpecific(BaseProduct baseProduct, Short productId, String storeName, String storeAddress) {
		this.baseProduct = baseProduct;
		this.productId = productId;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
	}



}
