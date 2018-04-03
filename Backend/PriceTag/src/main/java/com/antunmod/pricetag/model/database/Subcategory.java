package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subcategory")
public class Subcategory implements Serializable {

	private static final long serialVersionUID = 6907531712848342215L;

	@Id
	@Column(name = "subcategory_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subcategoryId;

	@Column(name = "subcategory_name")
	private String subcategoryName;

	public Subcategory() {
	}

	public Subcategory(int subcategoryId, String subcategoryName) {
		super();
		this.subcategoryId = subcategoryId;
		this.subcategoryName = subcategoryName;
	}

	public int getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
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
