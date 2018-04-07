package com.antunmod.pricetag.model.database;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * The class User represents a user. 
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 8234325017646826418L;

	@Id
	@Column(name = "user_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short userId;

	@Column(name = "user_name")
	private String name;

	@Column(name = "user_password")
	private String password;

	@Column(name = "user_mail")
	private String email;

	@Column(name = "signup_date")
	private Date signupDate;

	/*
	 * Users number of points which are used for checking user activity. A certain
	 * amount of points per week is mandatory in order to be able to search through
	 * the database. Points are received by adding or updating products.
	 */
	@Column(name = "points")
	private Short points;

	/*
	 * References the user_type table serial id that determines whether a user is a
	 * regular user or an admin.
	 */
	@Column(name = "user_type_ID")
	private byte userType;

	public User() {}
	
	public User(String name, String password, String email, Date signupDate, Short points, byte userType) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.signupDate = signupDate;
		this.points = points;
		this.userType = userType;
	}

	public String getUserName() {
		return name;
	}
	
	public Short getPoints() {
		return points;
	}

	public void setPoints(Short points) {
		this.points = points;
	}

}
