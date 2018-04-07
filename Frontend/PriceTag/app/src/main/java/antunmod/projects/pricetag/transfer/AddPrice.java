package antunmod.projects.pricetag.transfer;


/*
 * The AddPrice class contains data for saving a new price for an existing product in the given store.
 * 
 * Contains data which will be used to create the following new entries in database:
 * 		- price
 */

public class AddPrice {

	private short productSpecificId;
	private short storeSpecificId;
	private short userId;
	private float price;

	public short getProductSpecificId() {
		return productSpecificId;
	}

	public short getStoreSpecificId() {
		return storeSpecificId;
	}
	
}
