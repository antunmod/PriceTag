package antunmod.projects.pricetag.transfer;


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
public class AddProduct {

	private BaseProduct baseProduct;
	private Short producerId;
	private String productName;
	private Short storeSpecificId;
	private Short subcategoryId;

	public AddProduct(BaseProduct baseProduct, Short producerId, String productName, Short storeSpecificId,
			Short subcategoryId) {
		super();
		this.baseProduct = baseProduct;
		this.producerId = producerId;
		this.productName = productName;
		this.storeSpecificId = storeSpecificId;
		this.subcategoryId = subcategoryId;
	}


	public Short getSubcategoryId() {
		return subcategoryId;
	}

}
