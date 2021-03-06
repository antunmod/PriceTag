package antunmod.projects.pricetag.transfer;

/*
 * The AddStoreSpecificProductSpecific class contains data for saving a new product size to the new store address.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- store_specific
 * 		- product_specific
 * 		- product_store
 * 		- price
 */

import java.io.Serializable;

import antunmod.projects.pricetag.model.BaseProduct;

public class AddStoreSpecificProductSpecific implements Serializable{

	private BaseProduct baseProduct;
	private Short productId;
	private Short storeId;
	private String storeAddress;

	public AddStoreSpecificProductSpecific(BaseProduct baseProduct, Short productId, Short storeId,
			String storeAddress) {
		super();
		this.baseProduct = baseProduct;
		this.productId = productId;
		this.storeId = storeId;
		this.storeAddress = storeAddress;
	}

	public Short getStoreId() {
		return storeId;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public AddProductSpecific toAddProductSpecific(Short storeSpecificId) {
		return new AddProductSpecific(baseProduct, productId, storeSpecificId);
	}

}
