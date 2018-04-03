package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category_subcategory")
public class CategorySubcategory implements Serializable{

	
	private static final long serialVersionUID = 699107640033157900L;

	@Id
	@Column (name = "category_subcategory_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int categorySubcategoryId;
	
	@Column (name = "category_ID")
	private int categoryId;
	
	@Column (name = "subcategory_ID")
	private int subcategoryId;
	
	public CategorySubcategory() {}

	public CategorySubcategory(int categorySubcategoryId, int categoryId, int subcategoryId) {
		super();
		this.categorySubcategoryId = categorySubcategoryId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
	}

	public int getCategorySubcategoryId() {
		return categorySubcategoryId;
	}

	public void setCategorySubcategoryId(int categorySubcategoryId) {
		this.categorySubcategoryId = categorySubcategoryId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
