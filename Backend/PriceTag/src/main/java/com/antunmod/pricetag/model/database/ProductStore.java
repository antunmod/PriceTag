package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class represents a product in a specific store.
 */
@Entity
@Table(name = "product_store")
public class ProductStore implements Serializable {

	private static final long serialVersionUID = 7515006165127257791L;

	@Id
	@Column(name = "product_store_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short productStoreId;

	/*
	 * References a specific product from product_specific table.
	 */
	@Column(name = "product_specific_ID")
	private Short productSpecificId;

	/*
	 * References a specific store from store_specific table.
	 */
	@Column(name = "store_specific_ID")
	private Short storeSpecificId;

	public ProductStore() {}
	
	public ProductStore(Short productSpecificId, Short storeSpecificId) {
		super();
		this.productSpecificId = productSpecificId;
		this.storeSpecificId = storeSpecificId;
	}

	public Short getProductStoreId() {
		return productStoreId;
	}
	
	public Short getProductSpecificId() {
		return productSpecificId;
	}

}
