package antunmod.projects.pricetag.transfer;

/*
 * The StoreProductPrice class contains most recent price and user rating for a specific product in a specific store.
 * 
 */
public class StoreProductPrice {

	private String storeName;
	private String storeAddress;
	private String price;
	private String userRating;

	public StoreProductPrice(String storeName, String storeAddress, String price, String userRating) {
		super();
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.price = price;
		this.userRating = userRating;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public String getPrice() {
		return price;
	}

	public String getUserRating() {
		return userRating;
	}
}
