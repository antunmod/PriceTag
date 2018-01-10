package antunmod.projects.pricetag;

import java.io.Serializable;

/**
 * Created by antun on 1/10/2018.
 */

public class ProductDetails implements Serializable {

    private long productStoreId;
    private long productId;
    private int subcategoryId;
    private int categoryId;
    private int sectorId;

    private int storeId;
    private int userId;

    private String name;
    private String producer;
    private String barcode;
    private byte[] photo;
    private int size;
    private int sizeType;
    private float price;
    private String priceChangeDate;
    private float averagePrice;
    private int productUpdates;

    public ProductDetails(long productId, int subcategoryId, int categoryId,
                          int sectorId, int storeId, int userId, String name,
                          String producer, String barcode, byte[] photo,
                          int size, int sizeType, float price, String priceChangeDate,
                          float averagePrice, int productUpdates) {
        this.productId = productId;
        this.subcategoryId = subcategoryId;
        this.categoryId = categoryId;
        this.sectorId = sectorId;
        this.storeId = storeId;
        this.userId = userId;
        this.name = name;
        this.producer = producer;
        this.barcode = barcode;
        this.photo = photo;
        this.size = size;
        this.sizeType = sizeType;
        this.price = price;
        this.priceChangeDate = priceChangeDate;
        this.averagePrice = averagePrice;
        this.productUpdates = productUpdates;
    }

    public ProductDetails() {
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSizeType() {
        return sizeType;
    }

    public void setSizeType(int sizeType) {
        this.sizeType = sizeType;
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
