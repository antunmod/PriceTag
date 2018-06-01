package com.antunmod.pricetag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antunmod.pricetag.model.database.InformationFeedback;
import com.antunmod.pricetag.service.InformationFeedbackService;

/*
 * This is a Controller class for handling InformationFeedback data.
 */
@RestController
@RequestMapping("/informationFeedback")
public class InformationFeedbackController {

	@Autowired
	private InformationFeedbackService informationFeedbackService;
	
	/*
	 * This mapping will return InformationFeedback for given userId and priceId.
	 */
	@ResponseBody
	@PostMapping("")
	public ResponseEntity<InformationFeedback> getInformationFeedbackForUserAndPriceId(@RequestParam("userId") Short userId,
			@RequestParam("priceId") Integer priceId) {
		InformationFeedback informationFeedback = informationFeedbackService.getInformationFeedbackForUserAndPriceId(userId, priceId);
		return new ResponseEntity<InformationFeedback>(informationFeedback, HttpStatus.OK);
	}
	
	/*
	 * This mapping will save new InformationFeedback
	 */
	@ResponseBody
	@PostMapping("/save")
	public ResponseEntity<Boolean> saveInformationFeedback(@RequestBody InformationFeedback informationFeedback) {
		Boolean success = informationFeedbackService.saveInformationFeedback(informationFeedback);
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}
	
}
