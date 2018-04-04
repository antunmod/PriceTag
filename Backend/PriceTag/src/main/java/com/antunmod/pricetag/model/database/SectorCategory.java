package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class represents a category in a sector.
 */
@Entity
@Table(name = "sector_category")
public class SectorCategory implements Serializable {

	private static final long serialVersionUID = -639765082915541192L;

	@Id
	@Column(name = "sector_category_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte sectorCategoryId;

	/*
	 * References a sector.
	 */
	@Column(name = "sector_ID")
	private byte sectorId;

	/*
	 * References a category.
	 */
	@Column(name = "category_ID")
	private byte categoryId;

}
