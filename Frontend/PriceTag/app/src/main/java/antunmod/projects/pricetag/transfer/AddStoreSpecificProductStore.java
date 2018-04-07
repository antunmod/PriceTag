package antunmod.projects.pricetag.transfer;

/*
 * The AddStoreSpecificProductStore class contains data for saving an existing product with a new price 
 * to the new store address. 
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- store_specific
 * 		- product_store
 * 		- price
 */

public class AddStoreSpecificProductStore {

	private short productSpecificId;
	private byte storeId;
	private String storeAddress;
	private short userId;
	private float price;

	public AddStoreSpecificProductStore(byte storeId, String storeAddress, short userId, float price) {
		super();
		this.storeId = storeId;
		this.storeAddress = storeAddress;
		this.userId = userId;
		this.price = price;
	}

	public byte getStoreId() {
		return storeId;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

}
