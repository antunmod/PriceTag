package antunmod.projects.pricetag;

import java.io.Serializable;

/**
 * Created by antun on 1/12/2018.
 */

public class ProductStore implements Serializable{


    private long productStoreId;

    private int productId;

    private int storeId;

    private long userId;

    private String barcode;

    private String productDescription;

    private int photoId;

    private float productSize;

    private int productSizeId;

    private float price;

    private String priceChangeDate;

    private float averagePrice;

    private int productUpdates;

    public ProductStore() {}

    public ProductStore(long productStoreId, int productId, int storeId, long userId, String barcode,
                        String productDescription, int photoId, float productSize, int productSizeId,
                        float price, String priceChangeDate, float averagePrice, int productUpdates) {
        this.productStoreId = productStoreId;
        this.productId = productId;
        this.storeId = storeId;
        this.userId = userId;
        this.barcode = barcode;
        this.productDescription = productDescription;
        this.photoId = photoId;
        this.productSize = productSize;
        this.productSizeId = productSizeId;
        this.price = price;
        this.priceChangeDate = priceChangeDate;
        this.averagePrice = averagePrice;
        this.productUpdates = productUpdates;
    }

    public long getProductStoreId() {
        return productStoreId;
    }

    public void setProductStoreId(long productStoreId) {
        this.productStoreId = productStoreId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public float getProductSize() {
        return productSize;
    }

    public void setProductSize(float productSize) {
        this.productSize = productSize;
    }

    public int getProductSizeId() {
        return productSizeId;
    }

    public void setProductSizeId(int productSizeId) {
        this.productSizeId = productSizeId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPriceChangeDate() {
        return priceChangeDate;
    }

    public void setPriceChangeDate(String priceChangeDate) {
        this.priceChangeDate = priceChangeDate;
    }

    public float getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(float averagePrice) {
        this.averagePrice = averagePrice;
    }

    public int getProductUpdates() {
        return productUpdates;
    }

    public void setProductUpdates(int productUpdates) {
        this.productUpdates = productUpdates;
    }
}
