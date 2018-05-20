package com.antunmod.pricetag.model.transfer;

import java.io.Serializable;

/*
 * The AddProducerProduct class contains data for saving a new product by a new producer to the given store.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- producer
 * 		- product
 * 		- product_specific
 * 		- product_store
 * 		- price
 * 		- subcategory_product
 */

public class AddProducer implements Serializable {

	private static final long serialVersionUID = 7090837510204743944L;

	private BaseProduct baseProduct;
	private String producerName;
	private String productName;
	private Short storeSpecificId;
	private Short subcategoryId;

	public AddProducer() {
	}

	public AddProducer(BaseProduct baseProduct, String producerName, String productName, Short storeSpecificId,
			Short subcategoryId) {
		super();
		this.baseProduct = baseProduct;
		this.producerName = producerName;
		this.productName = productName;
		this.storeSpecificId = storeSpecificId;
		this.subcategoryId = subcategoryId;
	}

	public BaseProduct getBaseProduct() {
		return baseProduct;
	}

	public String getProducerName() {
		return producerName;
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

	public AddProduct toAddProduct(Short producerId) {
		return new AddProduct(baseProduct, producerId, productName, storeSpecificId, subcategoryId);
	}
}
