package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class represents a specific location of a store.
 */
@Entity
@Table(name = "store_specific")
public class StoreSpecific implements Serializable {

	private static final long serialVersionUID = 2695007085669807984L;

	@Id
	@Column(name = "store_specific_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short storeSpecificId;

	/*
	 * References the Store class id for finding store name.
	 */
	@Column(name = "store_ID")
	private byte storeId;

	@Column(name = "store_address")
	private String storeAddress;

	public StoreSpecific() {
		super();
	}

	public StoreSpecific(byte storeId, String storeAddress) {
		this.storeId = storeId;
		this.storeAddress = storeAddress;
	}
	
	public short getStoreSpecificId() {
		return storeSpecificId;
	}

}
