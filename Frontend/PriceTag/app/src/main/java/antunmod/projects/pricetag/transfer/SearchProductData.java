package antunmod.projects.pricetag.transfer;

import java.io.Serializable;

/*
 * The SearchProductData class contains data for a product which will be shown to user on request.
 */
public class SearchProductData implements Serializable{

	private Short productSpecificId;
	private String imageURI;
	private String producerName;
	private String productName;
	private String productDescription;
	private String productSize;

	public SearchProductData() {
		super();
	}

	public SearchProductData(Short productSpecificId, String imageURI, String producerName, String productName,
			String productDescription, String productSize) {
		super();
		this.productSpecificId = productSpecificId;
		this.imageURI = imageURI;
		this.producerName = producerName;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productSize = productSize;
	}

	public Short getProductSpecificId() {
		return productSpecificId;
	}

	public String getImageURI() {
		return imageURI;
	}

	public String getProducerName() {
		return producerName;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public String getProductSize() {
		return productSize;
	}

}
