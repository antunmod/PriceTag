package antunmod.projects.pricetag.transfer;


/*
 * The class AddProductSpecific saves a new size value for a product in a given store.

 * Contains data which will be used to create the following new entries in database:
 * 		- product_specific
 * 		- product_store
 * 		- price
 */

import antunmod.projects.pricetag.model.BaseProduct;

public class AddProductSpecific {

	private BaseProduct baseProduct;
	private Short productId;
	private Short storeSpecificId;

	public AddProductSpecific(BaseProduct baseProduct, Short productId, Short storeSpecificId) {
		this.baseProduct = baseProduct;
		this.productId = productId;
		this.storeSpecificId = storeSpecificId;
	}

}
