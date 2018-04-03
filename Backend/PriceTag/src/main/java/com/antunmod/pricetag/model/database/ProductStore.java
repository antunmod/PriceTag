package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_store")
public class ProductStore implements Serializable {

	private static final long serialVersionUID = 7515006165127257791L;

	@Id
	@Column(name = "product_store_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productStoreId;

	@Column(name = "product_specific_ID")
	private int productSpecificId;

	@Column(name = "store_location_ID")
	private int storeLocationId;

	public ProductStore() {
		super();
	}

	public ProductStore(long productStoreId, int productSpecificId, int storeLocationId) {
		super();
		this.productStoreId = productStoreId;
		this.productSpecificId = productSpecificId;
		this.storeLocationId = storeLocationId;
	}
	
	public long getProductStoreId() {
		return productStoreId;
	}

	public void setProductStoreId(long productStoreId) {
		this.productStoreId = productStoreId;
	}

	public int getProductSpecificId() {
		return productSpecificId;
	}

	public void setProductSpecificId(int productSpecificId) {
		this.productSpecificId = productSpecificId;
	}
	
	public int getStoreLocationId() {
		return storeLocationId;
	}

	public void setStoreLocationId(int storeLocationId) {
		this.storeLocationId = storeLocationId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
