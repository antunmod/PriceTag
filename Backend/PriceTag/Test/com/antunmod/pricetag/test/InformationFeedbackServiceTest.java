package com.antunmod.pricetag.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.antunmod.pricetag.PriceTagApplication;
import com.antunmod.pricetag.model.database.InformationFeedback;
import com.antunmod.pricetag.service.InformationFeedbackService;

/*
 * This class tests InformationFeedback data handling by InformationFeedbackService.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PriceTagApplication.class)
public class InformationFeedbackServiceTest {
	
	@Autowired
	InformationFeedbackService informationFeedbackService;
	
	private final Short TEST_USER_ID = 2;
	private final Integer TEST_PRICE_ID = 1;
	
	private final Short NEW_USER_ID = 2;
	private final Integer NEW_PRICE_ID = 1;
	private final String NEW_FEEDBACK = "P";
	
	
	//@Test
	public void testGetInformationFeedbackForUserAndPriceId() {
		Boolean success = false;
		InformationFeedback informationFeedback = informationFeedbackService.getInformationFeedbackForUserAndPriceId(
					TEST_USER_ID, TEST_PRICE_ID);
		if (informationFeedback != null)
			success = true;
		assertTrue(success);
	}
	
	@Test
	public void testSaveInformationFeedback() {
		Boolean success = false;
		InformationFeedback informationFeedback = new InformationFeedback(NEW_PRICE_ID, NEW_USER_ID, NEW_FEEDBACK);
		success = informationFeedbackService.saveInformationFeedback(informationFeedback);
		assertTrue(success);
	}

}
