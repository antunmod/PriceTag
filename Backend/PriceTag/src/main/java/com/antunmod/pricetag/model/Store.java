package com.antunmod.pricetag.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "store")
public class Store implements Serializable {

	private static final long serialVersionUID = -36312600639842865L;

	@Id
	@Column(name = "store_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storeId;

	@Column(name = "store_name")
	private String storeName;

	@Column(name = "store_address")
	private String storeAddress;

	public Store() {
	}

	public Store(int storeId, String storeName, String storeAddress) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
