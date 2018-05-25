package com.antunmod.pricetag.model.transfer;

import java.io.Serializable;

import com.antunmod.pricetag.model.database.Product;

/*
 * The class AddProduct saves a new product to the given store.

 * Contains data which will be used to create the following new entries in database:
 * 		- product
 * 		- product_specific
 * 		- product_store
 * 		- price
 * 		- subcategory_product
 */
public class AddProduct implements Serializable{

	private static final long serialVersionUID = 3331164250988889265L;
	
	private BaseProduct baseProduct;
	private Short producerId;
	private String productName;
	private Short storeSpecificId;
	private Short subcategoryId;

	public AddProduct() {}
	
	public AddProduct(BaseProduct baseProduct, Short producerId, String productName, Short storeSpecificId,
			Short subcategoryId) {
		this.baseProduct = baseProduct;
		this.producerId = producerId;
		this.productName = productName;
		this.storeSpecificId = storeSpecificId;
		this.subcategoryId = subcategoryId;
	}

	public BaseProduct getBaseProduct() {
		return baseProduct;
	}
	
	public Short getProducerId() {
		return producerId;
	}

	public String getProductName() {
		return productName;
	}

	public Short getStoreSpecificId() {
		return storeSpecificId;
	}

	public Short getSubcategoryId() {
		return subcategoryId;
	}
	
	public Product toProduct() {
		return new Product(producerId, productName);
	}

	public AddProductSpecific toAddProductSpecific(Short productId) {
		return new AddProductSpecific(baseProduct, productId, storeSpecificId);
	}

}
