package antunmod.projects.pricetag.transfer;


/*
 * The class AddProductSpecific saves a new size value for a product in a given store.

 * Contains data which will be used to create the following new entries in database:
 * 		- product_specific
 * 		- product_store
 * 		- price
 */

import java.io.Serializable;

import antunmod.projects.pricetag.model.BaseProduct;

public class AddProductSpecific implements Serializable {

	private BaseProduct baseProduct;
	private Short productId;
	private Short storeSpecificId;

	public AddProductSpecific(BaseProduct baseProduct, Short productId, Short storeSpecificId) {
		this.baseProduct = baseProduct;
		this.productId = productId;
		this.storeSpecificId = storeSpecificId;
	}

	public BaseProduct getBaseProduct() {
		return baseProduct;
	}

	public void setBaseProduct(BaseProduct baseProduct) {
		this.baseProduct = baseProduct;
	}

	public Short getProductId() {
		return productId;
	}

	public void setProductId(Short productId) {
		this.productId = productId;
	}

	public Short getStoreSpecificId() {
		return storeSpecificId;
	}

	public void setStoreSpecificId(Short storeSpecificId) {
		this.storeSpecificId = storeSpecificId;
	}
}
