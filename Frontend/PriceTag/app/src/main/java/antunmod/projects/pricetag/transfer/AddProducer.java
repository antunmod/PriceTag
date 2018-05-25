package antunmod.projects.pricetag.transfer;

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

import java.io.Serializable;

import antunmod.projects.pricetag.model.BaseProduct;

public class AddProducer implements Serializable{

	private BaseProduct baseProduct;
	private String producerName;
	private String productName;
	private Short storeSpecificId;
	private Short subcategoryId;

	public AddProducer() {}
	
	public AddProducer(BaseProduct baseProduct, String producerName, String productName, Short storeSpecificId,
			Short subcategoryId) {
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

	public AddProduct toAddProduct(Short producerId) {
		return new AddProduct(baseProduct, producerId, productName, storeSpecificId, subcategoryId);
	}
}
