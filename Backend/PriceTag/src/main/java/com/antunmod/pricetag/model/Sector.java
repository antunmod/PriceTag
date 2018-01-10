package com.antunmod.pricetag.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sector")
public class Sector implements Serializable{

	/**
	 * auto generated serialVersionUID
	 */
	private static final long serialVersionUID = 3939596959105641323L;

	@Id
	@Column (name = "sector_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sectorId;
	
	@Column (name = "sector_name")
	private String sectorName;
	
	public Sector() {}

	public Sector(int sectorId, String sectorName) {
		super();
		this.sectorId = sectorId;
		this.sectorName = sectorName;
	}

	public int getSectorId() {
		return sectorId;
	}

	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
