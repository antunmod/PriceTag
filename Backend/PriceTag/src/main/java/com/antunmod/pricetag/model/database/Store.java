package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class represents the store name.
 */
@Entity
@Table(name = "store")
public class Store implements Serializable {

	private static final long serialVersionUID = -36312600639842865L;

	@Id
	@Column(name = "store_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte storeId;

	@Column(name = "store_name")
	private String storeName;

	public Store() {}
	
	public Store(String storeName) {
		this.storeName = storeName;
	}
	
	public byte getStoreId() {
		return storeId;
	}
	
	public String getStoreName() {
		return storeName;
	}

}
