package com.antunmod.pricetag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.InformationFeedback;
import com.antunmod.pricetag.repo.InformationFeedbackRepository;
import com.antunmod.pricetag.repo.PriceRepository;

/*
 * This is the Service class which handles InformationFeedback requests by the Controller.
 */
@Service
public class InformationFeedbackService {

	@Autowired
	private InformationFeedbackRepository informationFeedbackRepository;
	
	@Autowired
	private PriceRepository priceRepository;
	
	/*
	 * This method will return all products in the database for the given filter.
	 */
	public InformationFeedback getInformationFeedbackForUserAndPriceId(Short userId, Integer priceId) {
		InformationFeedback informationFeedback = informationFeedbackRepository.findByUserAndPriceId(userId, priceId);
		return informationFeedback;
	}
	
	/*
	 * This method will save new InformationFeedback.
	 */
	public Boolean saveInformationFeedback(InformationFeedback informationFeedback) {
		if (informationFeedback.getFeedback() == null || informationFeedback.getFeedbackProviderUserId() == null || 
				informationFeedback.getPriceId() == null)
			return false;
		Short informationProviderUserId = priceRepository.findUserIdForId(informationFeedback.getPriceId());
		informationFeedback.setInformationProviderUserId(informationProviderUserId);
		InformationFeedback savedInformationFeedback = informationFeedbackRepository.save(informationFeedback);
		return informationFeedback != null;
	}
	
}
