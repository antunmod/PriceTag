package antunmod.projects.pricetag.transfer;

import java.io.Serializable;

/**
 * Created by antun on 5/30/2018.
 */

/*
 * Used when finding product for existing barcode upon product update.
 */
public class ProductInformation implements Serializable {

    private Short productSpecificId;
    private String photoURI;
    private String productDescription;

    public ProductInformation(Short productSpecificId, String photoURI, String productDescription) {
        super();
        this.productSpecificId = productSpecificId;
        this.photoURI = photoURI;
        this.productDescription = productDescription;
    }

    public Short getProductSpecificId() {
        return productSpecificId;
    }

    public String getPhotoURI() {
        return photoURI;
    }

    public String getProductDescription() {
        return productDescription;
    }

}

