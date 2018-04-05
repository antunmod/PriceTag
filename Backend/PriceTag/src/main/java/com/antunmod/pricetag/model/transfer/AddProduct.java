package com.antunmod.pricetag.model.transfer;

import com.antunmod.pricetag.model.database.Product;
import com.antunmod.pricetag.model.database.ProductSpecific;

/*
 * The class AddProduct saves a new product to the given store.

 * Contains data which will be used to create the following new entries in database:
 * 		- product
 * 		- product_specific
 * 		- product_store
 * 		- price
 * 		- subcategory_product
 */
public class AddProduct {

	private BaseProduct baseProduct;
	private short producerId;
	private String productName;
	private short storeSpecificId;
	private short subcategoryId;

	public AddProduct(BaseProduct baseProduct, short producerId, String productName, short storeSpecificId,
			short subcategoryId) {
		super();
		this.baseProduct = baseProduct;
		this.producerId = producerId;
		this.productName = productName;
		this.storeSpecificId = storeSpecificId;
		this.subcategoryId = subcategoryId;
	}

	public Product toProduct() {
		return new Product(producerId, productName);
	}

	public AddProductSpecific toAddProductSpecific(short productId) {
		return new AddProductSpecific(baseProduct, productId, storeSpecificId);
	}

	public short getSubcategoryId() {
		return subcategoryId;
	}

}
