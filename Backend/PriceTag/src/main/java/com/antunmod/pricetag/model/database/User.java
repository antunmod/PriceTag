package com.antunmod.pricetag.model.database;

import java.io.Serializable;
import java.util.Date;

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
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "signup_date")
	private String signupDate;

	@Column(name = "rating")
	private Float rating;
	
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
	@Column(name = "user_type_id")
	private Short userType;

	public User() {}
	
	public User(String name, String password, String email, String signupDate) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.signupDate = signupDate;
		
		/*
		 * Default values for new users.
		 */
		this.rating = 0.0f;
		this.points = 0;
		this.userType = 1;
	}

	public String getName() {
		return name;
	}
	
	public Short getPoints() {
		return points;
	}

	public void setPoints(Short points) {
		this.points = points;
	}
	
	public String getEmail() {
		return email;
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Short getUserType() {
		return userType;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
