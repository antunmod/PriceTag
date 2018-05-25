package com.antunmod.pricetag.model.transfer;

import java.io.Serializable;

/*
 * The AddStoreSpecificProductSpecific class contains data for saving a new product size to the new store address.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- store_specific
 * 		- product_specific
 * 		- product_store
 * 		- price
 */

public class AddStoreSpecificProductSpecific implements Serializable {

	private static final long serialVersionUID = -2211114704378920399L;

	private BaseProduct baseProduct;
	private Short productId;
	private Short storeId;
	private String storeAddress;

	public AddStoreSpecificProductSpecific() {
	}

	public AddStoreSpecificProductSpecific(BaseProduct baseProduct, Short productId, Short storeId,
			String storeAddress) {
		super();
		this.baseProduct = baseProduct;
		this.productId = productId;
		this.storeId = storeId;
		this.storeAddress = storeAddress;
	}

	public BaseProduct getBaseProduct() {
		return baseProduct;
	}

	public Short getProductId() {
		return productId;
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
