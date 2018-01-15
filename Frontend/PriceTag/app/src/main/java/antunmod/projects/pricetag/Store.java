package antunmod.projects.pricetag;

/**
 * Created by antun on 1/15/2018.
 */

public class Store {

    private int storeId;

    private String storeName;

    private String storeAddress;

    public Store() {
    }

    public Store(int storeId, String storeName, String storeAddress) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}
