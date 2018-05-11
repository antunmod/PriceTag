package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * The class InformationFeedback represents user feedback.
 */
@Entity
@Table(name = "information_feedback")
public class InformationFeedback implements Serializable {

	private static final long serialVersionUID = 7554836347563163080L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "information_provider_user_id")
	private Short informationProviderUserId;

	@Column(name = "feedback_provider_user_id")
	private Short feedbackProviderUserId;

	/*
	 * Feedback provided by user with feedbackProviderUserId. Can be POSITIVE(P) or
	 * NEGATIVE (N).
	 */
	@Column(name = "feedback")
	private String feedback;

	public InformationFeedback() {
	}

	public InformationFeedback(Short informationProviderUserId, Short feedbackProviderUserId, String feedback) {
		super();
		this.informationProviderUserId = informationProviderUserId;
		this.feedbackProviderUserId = feedbackProviderUserId;
		this.feedback = feedback;
	}
	
	public String getFeedback() {
		return feedback;
	}

}
