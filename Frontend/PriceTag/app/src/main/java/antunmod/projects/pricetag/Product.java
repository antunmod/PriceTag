package antunmod.projects.pricetag;

import java.io.Serializable;

/**
 * Created by antun on 1/12/2018.
 */

public class Product implements Serializable {

    private int productId;
    private String productName;
    private String producer;

    public Product() {}

    public Product(int productId, String productName, String producer) {
        this.productId = productId;
        this.productName = productName;
        this.producer = producer;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
}
