package com.antunmod.pricetag.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.InformationFeedback;
import com.antunmod.pricetag.model.transfer.SearchProductData;
import com.antunmod.pricetag.repo.InformationFeedbackRepository;

/*
 * This is the Service class which handles InformationFeedback requests by the Controller.
 */
@Service
public class InformationFeedbackService {

	@Autowired
	private InformationFeedbackRepository informationFeedbackRepository;
	
	/*
	 * This method will return all products in the database for the given filter.
	 */
	public InformationFeedback getInformationFeedbackForUserAndPriceId(Short userId, Integer priceId) {
		InformationFeedback informationFeedback = informationFeedbackRepository.findByUserAndPriceId(userId, priceId);
		return informationFeedback;
	}
	
}
