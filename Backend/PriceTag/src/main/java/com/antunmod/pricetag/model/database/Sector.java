package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class represents a sector in the product hierarchy. Sectors are on the highest level of that hierarchy.
 * 
 * Examples of sectors:
 * 		- Supermarkets
 * 		- Kiosks
 */
@Entity
@Table(name = "sector")
public class Sector implements Serializable {

	private static final long serialVersionUID = 3939596959105641323L;

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
