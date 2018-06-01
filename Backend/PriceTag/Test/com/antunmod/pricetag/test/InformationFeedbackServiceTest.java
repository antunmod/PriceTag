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
	
	@Test
	public void testGetInformationFeedbackForUserAndPriceId() {
		Boolean correct = false;
		InformationFeedback informationFeedback = informationFeedbackService.getInformationFeedbackForUserAndPriceId(
					TEST_USER_ID, TEST_PRICE_ID);
		if (informationFeedback != null)
			correct = true;
		assertTrue(correct);
	}

}
