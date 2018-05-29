package com.antunmod.pricetag.model.transfer;

import java.io.Serializable;
import java.sql.Date;

public class UserInformation implements Serializable{

	private String name;
	private String email;
	private Short points;
	private String informationValidity;
	private Integer feedbacksReceived;
	private Integer feedbacksGiven;
	private String description;
	private Date signupDate;
	
	private static final long serialVersionUID = 2116305284231303350L;

	public UserInformation() {
	}

	public UserInformation(String name, String email, Short points, String informationValidity,
			Integer feedbacksReceived, Integer feedbacksGiven, String description, Date signupDate) {
		super();
		this.name = name;
		this.email = email;
		this.points = points;
		this.informationValidity = informationValidity;
		this.feedbacksReceived = feedbacksReceived;
		this.feedbacksGiven = feedbacksGiven;
		this.description = description;
		this.signupDate = signupDate;
	}
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Short getPoints() {
		return points;
	}

	public String getInformationValidity() {
		return informationValidity;
	}

	public Integer getFeedbacksReceived() {
		return feedbacksReceived;
	}

	public Integer getFeedbacksGiven() {
		return feedbacksGiven;
	}

	public String getDescription() {
		return description;
	}

	public Date getSignupDate() {
		return signupDate;
	}
	
	
}
