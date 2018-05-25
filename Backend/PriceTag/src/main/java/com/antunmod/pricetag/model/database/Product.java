package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class represents product names and their producers.
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = -2721891796273863721L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	/*
	 * References producer_ID in producer table
	 */
	@Column(name = "producer_id")
	private Short producerId;

	@Column(name = "name")
	private String name;

	public Product() {}

	public Product(Short producerId, String name) {
		super();
		this.producerId = producerId;
		this.name = name;
	}

	public Short getId() {
		return id;
	}

	public Short getProducerId() {
		return producerId;
	}

	public String getName() {
		return name;
	}
	
	

}
