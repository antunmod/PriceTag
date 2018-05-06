package antunmod.projects.pricetag.model;

/**
 * Created by antun on 5/5/2018.
 */

public class ProductData {

    private BaseProduct baseProduct;

    private Short productId;
    private String productName;

    private Short productSpecificId;
    private Float price;

    private Short producerId;
    private String producerName;


    private Byte storeId;
    private String storeName;

    private Short storeSpecificId;
    private String storeAddress;

    private Short subcategoryId;

    public ProductData() {
        this.baseProduct = new BaseProduct();
    }

    public BaseProduct getBaseProduct() {
        return baseProduct;
    }

    public void setBaseProduct(BaseProduct baseProduct) {
        this.baseProduct = baseProduct;
    }

    public Short getProductId() {
        return productId;
    }

    public void setProductId(Short productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Short getProductSpecificId() {
        return productSpecificId;
    }

    public void setProductSpecificId(Short productSpecificId) {
        this.productSpecificId = productSpecificId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Short getProducerId() {
        return producerId;
    }

    public void setProducerId(Short producerId) {
        this.producerId = producerId;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public Byte getStoreId() {
        return storeId;
    }

    public void setStoreId(Byte storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Short getStoreSpecificId() {
        return storeSpecificId;
    }

    public void setStoreSpecificId(Short storeSpecificId) {
        this.storeSpecificId = storeSpecificId;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Short getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Short subcategoryId) {
        this.subcategoryId = subcategoryId;
    }
}
