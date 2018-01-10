package com.antunmod.pricetag.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="suggested_categorization")
public class SuggestedCategorization implements Serializable{
	
	private static final long serialVersionUID = 6798874615378134031L;

	@Column (name = "sector_name")
	private String sectorName;
	
	@Column (name = "category_name")
	private String categoryName;
	
	@Column (name = "subcategory_name")
	private String subcategoryName;
	
	public SuggestedCategorization() {}

	public SuggestedCategorization(String sectorName, String categoryName, String subcategoryName) {
		super();
		this.sectorName = sectorName;
		this.categoryName = categoryName;
		this.subcategoryName = subcategoryName;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
