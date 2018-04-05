package com.antunmod.pricetag.model.transfer;

import com.antunmod.pricetag.model.database.Price;
import com.antunmod.pricetag.model.database.ProductSpecific;
import com.antunmod.pricetag.model.database.ProductStore;
import com.antunmod.pricetag.repo.PriceRepository;
import com.antunmod.pricetag.repo.ProductSpecificRepository;
import com.antunmod.pricetag.repo.ProductStoreRepository;

/*
 * The class AddProductSpecific saves a new size value for a product in a given store.

 * Contains data which will be used to create the following new entries in database:
 * 		- product_specific
 * 		- product_store
 * 		- price
 */

public class AddProductSpecific {

	private BaseProduct baseProduct;
	private short productId;
	private short storeSpecificId;

	public AddProductSpecific(BaseProduct baseProduct, short productId, short storeSpecificId) {
		this.baseProduct = baseProduct;
		this.productId = productId;
		this.storeSpecificId = storeSpecificId;
	}

	public ProductSpecific toProductSpecific() {
		return new ProductSpecific(baseProduct, productId);
	}

	public ProductStore toProductStore(short productSpecificId) {
		return new ProductStore(productSpecificId, storeSpecificId);
	}

	public Price toPrice(short productStoreId) {
		return new Price(productStoreId, baseProduct.getUserId(), baseProduct.getPrice());
	}

}
