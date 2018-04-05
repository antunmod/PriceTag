package com.antunmod.pricetag.model.transfer;

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

public class AddProducer {

	private BaseProduct baseProduct;
	private String producerName;
	private String productName;
	private short storeSpecificId;
	private short subcategoryId;

	public AddProducer(BaseProduct baseProduct, String producerName, String productName, short storeSpecificId,
			short subcategoryId) {
		super();
		this.baseProduct = baseProduct;
		this.producerName = producerName;
		this.productName = productName;
		this.storeSpecificId = storeSpecificId;
		this.subcategoryId = subcategoryId;
	}

	public String getProducerName() {
		return producerName;
	}

	public AddProduct toAddProduct(short producerId) {
		return new AddProduct(baseProduct, producerId, productName, storeSpecificId, subcategoryId);
	}
}
