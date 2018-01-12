package antunmod.projects.pricetag;

import java.io.Serializable;

/**
 * Created by antun on 1/12/2018.
 */

public class UpdateProduct implements Serializable{

    private long productStoreId;

    private long userId;

    private String photo;

    private String producer;

    private String name;

    private int size;

    private String sizeType;

    private float price;

    private float averagePrice;

    private int productUpdates;

    private String priceChangeDate;

    public UpdateProduct(){}

    public UpdateProduct(long productStoreId, long userId, String photo, String producer,
                         String name, int size, String sizeType, float price, float averagePrice,
                         int productUpdates, String priceChangeDate) {
        this.productStoreId = productStoreId;
        this.userId = userId;
        this.photo = photo;
        this.producer = producer;
        this.name = name;
        this.size = size;
        this.sizeType = sizeType;
        this.price = price;
        this.averagePrice = averagePrice;
        this.productUpdates = productUpdates;
        this.priceChangeDate = priceChangeDate;
    }

    public long getProductStoreId() {
        return productStoreId;
    }

    public void setProductStoreId(long productStoreId) {
        this.productStoreId = productStoreId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSizeType() {
        return sizeType;
    }

    public void setSizeType(String sizeType) {
        this.sizeType = sizeType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public String getPriceChangeDate() {
        return priceChangeDate;
    }

    public void setPriceChangeDate(String priceChangeDate) {
        this.priceChangeDate = priceChangeDate;
    }
}