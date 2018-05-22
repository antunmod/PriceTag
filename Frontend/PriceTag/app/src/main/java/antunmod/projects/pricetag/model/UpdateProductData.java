package antunmod.projects.pricetag.model;

import java.io.Serializable;

import antunmod.projects.pricetag.transfer.AddPrice;
import antunmod.projects.pricetag.transfer.AddStoreProductStore;
import antunmod.projects.pricetag.transfer.AddStoreSpecificProductStore;

/**
 * Created by antun on 5/22/2018.
 */

public class UpdateProductData implements Serializable{

    private Short productSpecificId;
    private Short storeId;
    private String storeName;
    private Short storeSpecificId;
    private String storeAddress;
    private Short userId;
    private Float price;

    public UpdateProductData(ProductData productData) {
        this(productData.getProductSpecificId(), productData.getStoreId(), productData.getStoreName(), productData.getStoreSpecificId(), productData.getStoreAddress(),
                productData.getBaseProduct().getUserId());
    }

    public UpdateProductData(Short productSpecificId, Short storeId, String storeName, Short storeSpecificId, String storeAddress, Short userId) {
        this.productSpecificId = productSpecificId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeSpecificId = storeSpecificId;
        this.storeAddress = storeAddress;
        this.userId = userId;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getStoreName() {
        return storeName;
    }

    public Short getStoreId() {
        return storeId;
    }

    public AddStoreProductStore toAddStoreProductStore() {
        return new AddStoreProductStore(productSpecificId, storeName, storeAddress, userId, price);
    }

    public AddStoreSpecificProductStore toAddStoreSpecificProductStore() {
        return new AddStoreSpecificProductStore(storeId, storeAddress, userId, price);
    }

    public AddPrice toAddPrice() {
        return new AddPrice(productSpecificId, storeSpecificId, userId, price);
    }
}
