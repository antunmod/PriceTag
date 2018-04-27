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
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;

	/*
	 * References a sector.
	 */
	@Column(name = "sector_id")
	private byte sectorId;

	/*
	 * References a category.
	 */
	@Column(name = "category_id")
	private byte categoryId;

	public byte getSectorId() {
		return sectorId;
	}
	
}
