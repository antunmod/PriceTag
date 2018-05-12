package antunmod.projects.pricetag.transfer;

/*
 * The AddStoreProduct class contains data for saving a new product to the new store name at a new store address.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- store
 * 		- store_specific
 * 		- product_specific
 * 		- product_store
 * 		- price
 * 		- subcategory_product
 */

import antunmod.projects.pricetag.model.BaseProduct;

public class AddStoreProduct {

	private BaseProduct baseProduct;
	private Short producerId;
	private String productName;
	private String storeName;
	private String storeAddress;
	private Short subcategoryId;

	public AddStoreProduct(BaseProduct baseProduct, Short producerId, String productName, String storeName, String storeAddress, Short subcategoryId) {
		this.baseProduct = baseProduct;
		this.producerId = producerId;
		this.productName = productName;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.subcategoryId = subcategoryId;
	}

	public String getStoreName() {
		return storeName;
	}
	
	public AddStoreSpecificProduct toAddStoreSpecificProduct(byte storeId) {
		return new AddStoreSpecificProduct(baseProduct, producerId, productName, storeId, storeAddress, subcategoryId);
	}
}
