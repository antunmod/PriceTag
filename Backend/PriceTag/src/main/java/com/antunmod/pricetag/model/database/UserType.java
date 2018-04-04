package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * The class UserType specifies which role users can have. The roles are:
 * 		- USER
 * 		- ADMIN
 * 
 * Admins are responsible for verifying validity of added product to the database. A regular user can become an admin by 
 * contributing more than most of users.
 */
@Entity
@Table(name = "user_type")
public class UserType implements Serializable {

	private static final long serialVersionUID = -6937190304239864675L;

	@Id
	@Column(name = "user_type_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte userTypeId;

	@Column(name = "user_type_description")
	private String userTypeDescription;

}
