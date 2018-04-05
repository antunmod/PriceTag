package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class represents a subcategory in the product hierarchy. Subcategories are on the lowest level of that hierarchy.
 * 
 * Examples of subcategories (in Supermarkets in Beverages):
 * 		- Beer
 * 		- Wine
 * 		- Strong alcoholic beverages
 */
@Entity
@Table(name = "subcategory")
public class Subcategory implements Serializable {

	private static final long serialVersionUID = 6907531712848342215L;

	@Id
	@Column(name = "subcategory_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short subcategoryId;

	@Column(name = "subcategory_name")
	private String subcategoryName;

	public String getSubcategoryName() {
		return subcategoryName;
	}
}
