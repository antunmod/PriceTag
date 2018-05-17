package com.antunmod.pricetag.model.transfer;

/*
 * The SearchFIlter class contains filter data sent from frontend.
 */
public class SearchFilter {

	private String categoryName;
	private String subcategoryName;
	private String producerName;
	private String productName;
	private String storeName;

	public SearchFilter() {
	}
	
	public SearchFilter(String categoryName, String subcategoryName, String producerName, String productName,
			String storeName) {
		super();
		this.categoryName = categoryName;
		this.subcategoryName = subcategoryName;
		this.producerName = producerName;
		this.productName = productName;
		this.storeName = storeName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public String getProducerName() {
		return producerName;
	}

	public String getProductName() {
		return productName;
	}

	public String getStoreName() {
		return storeName;
	}

}
