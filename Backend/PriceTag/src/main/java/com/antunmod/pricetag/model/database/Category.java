package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class represents a category in the product hierarchy. Categories are on the second level of that hierarchy.
 * 
 * Examples of categories (in Supermarkets):
 * 		- Beverages
 * 		- Frozen foods
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = -1004611976928679970L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;

	@Column(name = "name")
	private String name;

	public String getName() {
		return name;
	}
}
