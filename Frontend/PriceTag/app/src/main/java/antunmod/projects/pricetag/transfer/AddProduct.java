package antunmod.projects.pricetag.transfer;


import java.io.Serializable;

import antunmod.projects.pricetag.model.BaseProduct;

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

	private BaseProduct baseProduct;
	private Short producerId;
	private String productName;
	private Short storeSpecificId;
	private Short subcategoryId;

	public AddProduct(BaseProduct baseProduct, Short producerId, String productName, Short storeSpecificId,
			Short subcategoryId) {
		this.baseProduct = baseProduct;
		this.producerId = producerId;
		this.productName = productName;
		this.storeSpecificId = storeSpecificId;
		this.subcategoryId = subcategoryId;
	}

	public Short getSubcategoryId() {
		return subcategoryId;
	}

	public BaseProduct getBaseProduct() {
		return baseProduct;
	}

	public void setBaseProduct(BaseProduct baseProduct) {
		this.baseProduct = baseProduct;
	}

	public Short getProducerId() {
		return producerId;
	}

	public void setProducerId(Short producerId) {
		this.producerId = producerId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Short getStoreSpecificId() {
		return storeSpecificId;
	}

	public void setStoreSpecificId(Short storeSpecificId) {
		this.storeSpecificId = storeSpecificId;
	}

	public void setSubcategoryId(Short subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
}
