package com.antunmod.pricetag.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.antunmod.pricetag.PriceTagApplication;
import com.antunmod.pricetag.model.transfer.SearchFilter;
import com.antunmod.pricetag.model.transfer.SearchProductData;
import com.antunmod.pricetag.service.SearchService;

/*
 * This class tests getting the correct SearchProductData by SearchService.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PriceTagApplication.class)
public class SearchServiceTest {

	private final String MEAL_PREPARATION = "Priprema jela";
	private final String OILS = "Ulja";
	private final String ZVIJEZDA = "Zvijezda";
	private final String SUNFLOWER_OIL = "Suncokretovo ulje";
	private final String KONZUM = "KONZUM"; 
	private final String LITER = "1 l";
	
	private SearchFilter searchFilter;
	
	@Autowired
	SearchService searchService;
	
	@Before
	public void setupSearchFilter() {
		searchFilter = new SearchFilter(
				MEAL_PREPARATION,
				OILS,
				ZVIJEZDA,
				SUNFLOWER_OIL,
				KONZUM);
	}
	
	@Test
	public void testGetProducts() {
		Boolean contained = false;
		ArrayList<SearchProductData> searchProductDataList = searchService.getProducts(searchFilter);
		for (SearchProductData searchProductData : searchProductDataList) {
			if (searchProductData.getProductSize().equals(LITER))
				contained = true;
			break;
		}
		assertTrue(contained);
		
	}
	
}
