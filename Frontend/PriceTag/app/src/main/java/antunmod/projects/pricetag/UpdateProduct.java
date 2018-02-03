package antunmod.projects.pricetag;

import android.support.annotation.ArrayRes;
import android.support.v4.media.MediaMetadataCompat;

import java.io.Serializable;

import retrofit2.http.Part;

/**
 * Created by antun on 1/12/2018.
 */

public class UpdateProduct implements Serializable {

    private long productStoreId;

    private long userId;

    private int photoId;

    private String producer;

    private String name;

    private float size;

    private String sizeType;

    private float price;

    private float averagePrice;

    private int productUpdates;

    private String priceChangeDate;

    public UpdateProduct() {
    }

    public UpdateProduct(long productStoreId, long userId, int photoId, String producer,
                         String name, float size, String sizeType, float price, float averagePrice,
                         int productUpdates, String priceChangeDate) {
        this.productStoreId = productStoreId;
        this.userId = userId;
        this.photoId = photoId;
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

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
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

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
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
