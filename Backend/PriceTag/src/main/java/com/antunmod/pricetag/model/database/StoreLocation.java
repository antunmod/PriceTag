package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "store_location")
public class StoreLocation implements Serializable {

	private static final long serialVersionUID = 2695007085669807984L;

	@Id
	@Column(name = "store_location_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storeLocationId;

	@Column(name = "store_ID")
	private int storeId;

	@Column(name = "store_address")
	private String storeAddress;

	public StoreLocation() {
		super();
	}

	public StoreLocation(int storeLocationId, int storeId, String storeAddress) {
		super();
		this.storeLocationId = storeLocationId;
		this.storeId = storeId;
		this.storeAddress = storeAddress;
	}

	public int getStoreLocationId() {
		return storeLocationId;
	}

	public void setStoreLocationId(int storeLocationId) {
		this.storeLocationId = storeLocationId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
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
