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

	/*
	 * StoreId variable type is set to Short, although Byte would suffice. This is due to Hibernate limitation on 
	 * inserting objects with Byte id to database.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	@Column(name = "name")
	private String name;

	public Store() {}
	
	public Store(String name) {
		this.name = name;
	}
	
	public Short getStoreId() {
		return id;
	}
	
	public String getStoreName() {
		return name;
	}

}
