package antunmod.projects.pricetag.transfer;

/*
 * The AddStoreSpecificProductSpecific class contains data for saving a new product size to the new store address.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- store_specific
 * 		- product_specific
 * 		- product_store
 * 		- price
 */

public class AddStoreSpecificProductSpecific {

	private BaseProduct baseProduct;
	private short productId;
	private byte storeId;
	private String storeAddress;

	public AddStoreSpecificProductSpecific(BaseProduct baseProduct, short productId, byte storeId,
			String storeAddress) {
		super();
		this.baseProduct = baseProduct;
		this.productId = productId;
		this.storeId = storeId;
		this.storeAddress = storeAddress;
	}

	public byte getStoreId() {
		return storeId;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public AddProductSpecific toAddProductSpecific(short storeSpecificId) {
		return new AddProductSpecific(baseProduct, productId, storeSpecificId);
	}

}
