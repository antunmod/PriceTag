package com.antunmod.pricetag.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sector_category")
public class SectorCategory implements Serializable {

	private static final long serialVersionUID = -639765082915541192L;

	@Id
	@Column(name = "sector_category_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sectorCategoryId;

	@Column(name = "sector_ID")
	private int sectorId;

	@Column(name = "category_ID")
	private int categoryId;

	public SectorCategory() {
	}

	public SectorCategory(int sectorCategoryId, int sectorId, int categoryId) {
		super();
		this.sectorCategoryId = sectorCategoryId;
		this.sectorId = sectorId;
		this.categoryId = categoryId;
	}

	public int getSectorCategoryId() {
		return sectorCategoryId;
	}

	public void setSectorCategoryId(int sectorCategoryId) {
		this.sectorCategoryId = sectorCategoryId;
	}

	public int getSectorId() {
		return sectorId;
	}

	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
