package antunmod.projects.pricetag.model;

import java.io.Serializable;
import java.util.Date;

/*
 * The class User represents a user. 
 */

public class User implements Serializable {

	private static final long serialVersionUID = 8234325017646826418L;

	private Short id;

	private String name;

	private String password;

	private String email;

	private Date signupDate;

	private Float rating;
	
	/*
	 * Users number of points which are used for checking user activity. A certain
	 * amount of points per week is mandatory in order to be able to search through
	 * the database. Points are received by adding or updating products.
	 */
	private Short points;

	/*
	 * References the user_type table serial id that determines whether a user is a
	 * regular user or an admin.
	 */
	private Short userType;

	public User() {}
	
	public User(String name, String password, String email, Date signupDate) {
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

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String name) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
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

}
