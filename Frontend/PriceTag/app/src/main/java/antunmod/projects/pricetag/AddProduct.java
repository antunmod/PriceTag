package antunmod.projects.pricetag;

/**
 * Created by antun on 2/3/2018.
 */

public class AddProduct {

    private String productName;
    private String producer;
    private byte[] photo;
    private int storeId;
    private long userId;
    private String barcode;
    private String productDescription;
    private float productSize;
    private int productSizeId;
    private float price;
    private String priceChangeDate;
    private float averagePrice;
    private int subcategoryId;

    public AddProduct() {
    }

    public AddProduct(String productName, String producer, byte[] photo, int storeId,
                      long userId, String barcode, String productDescription, float productSize,
                      int productSizeId, float price, String priceChangeDate, float averagePrice, int subcategoryId) {
        this.productName = productName;
        this.producer = producer;
        this.photo = photo;
        this.storeId = storeId;
        this.userId = userId;
        this.barcode = barcode;
        this.productDescription = productDescription;
        this.productSize = productSize;
        this.productSizeId = productSizeId;
        this.price = price;
        this.priceChangeDate = priceChangeDate;
        this.averagePrice = averagePrice;
        this.subcategoryId = subcategoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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

    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }
}
