package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class represents a subcategory in a category.
 */
@Entity
@Table(name = "category_subcategory")
public class CategorySubcategory implements Serializable {

	private static final long serialVersionUID = 699107640033157900L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	/*
	 * References a category.
	 */
	@Column(name = "category_id")
	private byte categoryId;

	/*
	 * References a subcategory.
	 */
	@Column(name = "subcategory_id")
	private Short subcategoryId;

	public byte getCategoryId() {
		return categoryId;
	}
	
}
