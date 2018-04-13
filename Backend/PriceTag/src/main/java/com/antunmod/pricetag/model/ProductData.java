package com.antunmod.pricetag.model;

import com.antunmod.pricetag.model.transfer.AddProducer;
import com.antunmod.pricetag.model.transfer.AddStoreProducer;
import com.antunmod.pricetag.model.transfer.AddStoreSpecificProducer;
import com.antunmod.pricetag.model.transfer.BaseProduct;

/*
 * This class will store all the neccessary data to create each one of Add classes.
 * Depending on which variables are set, a conversion will be possible to a specific class.
 */
public class ProductData {

	private BaseProduct baseProduct;
	private Short productSpecificId;

	private Short productId;
	private String productName;

	private Short producerId;
	private String producerName;

	private Short storeSpecificId;
	private String storeName;
	private String storeAddress;
	private Short storeId;

	private Short subcategoryId;

	public ProductData() {
	}

	public ProductData(BaseProduct baseProduct, Short productSpecificId, Short productId, String productName,
			Short producerId, String producerName, Short storeSpecificId, String storeName, String storeAddress,
			Short storeId, Short subcategoryId) {
		super();
		this.baseProduct = baseProduct;
		this.productSpecificId = productSpecificId;
		this.productId = productId;
		this.productName = productName;
		this.producerId = producerId;
		this.producerName = producerName;
		this.storeSpecificId = storeSpecificId;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.storeId = storeId;
		this.subcategoryId = subcategoryId;
	}

	public BaseProduct getBaseProduct() {
		return baseProduct;
	}

	public Short getProductSpecificId() {
		return productSpecificId;
	}

	public Short getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
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

	public Short getStoreSpecificId() {
		return storeSpecificId;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public Short getStoreId() {
		return storeId;
	}

	public Short getSubcategoryId() {
		return subcategoryId;
	}

	public AddProducer toAddProducer() {
		return new AddProducer(baseProduct, producerName, productName, storeSpecificId, subcategoryId);
	}
	
	public AddStoreSpecificProducer toAddStoreSpecificProducer() {
		return new AddStoreSpecificProducer(baseProduct, producerName, productName, storeId, storeAddress, subcategoryId);
	}

	public AddStoreProducer toAddStoreProducer() {
		return new AddStoreProducer(baseProduct, producerName, productName, storeName, storeAddress, subcategoryId);
	}
	
	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}
	
	public void setStoreSpecificId(Short storeSpecificId) {
		this.storeSpecificId = storeSpecificId;
	}

}
