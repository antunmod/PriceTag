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
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	/*
	 * References the Store class id for finding store name.
	 */
	@Column(name = "store_ID")
	private Short storeId;

	@Column(name = "address")
	private String address;

	public StoreSpecific() {
		super();
	}

	public StoreSpecific(Short storeId, String address) {
		this.storeId = storeId;
		this.address = address;
	}
	
	public Short getId() {
		return id;
	}
	
	public String getAddress() {
		return address;
	}

}
