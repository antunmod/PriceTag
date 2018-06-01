package antunmod.projects.pricetag.transfer;

import java.io.Serializable;

/*
 * The StoreProductPrice class contains most recent price and user rating for a specific product in a specific store.
 * 
 */
public class StoreProductPrice implements Serializable{

	private Integer priceId;
	private Short userId;
	private String storeName;
	private String storeAddress;
	private String price;
	private String userRating;

	public StoreProductPrice(Integer priceId, Short userId, String storeName, String storeAddress, String price, String userRating) {
		super();
		this.priceId = priceId;
		this.userId = userId;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.price = price;
		this.userRating = userRating;
	}

	public Integer getPriceId() {
		return priceId;
	}

	public Short getUserId() {
		return userId;
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
