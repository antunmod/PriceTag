package com.antunmod.pricetag.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.antunmod.pricetag.PriceTagApplication;
import com.antunmod.pricetag.model.transfer.ProductInformation;
import com.antunmod.pricetag.service.SimpleService;

/*
 * This class tests getting the correct data by SimpleService.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PriceTagApplication.class)
public class SimpleServiceTest {

	/*
	 * Final variables used for testing.
	 */
	private final String SUPERMARKETS_STRING = "Supermarketi";
	private final String MEAL_PREPARATION_STRING = "Priprema jela";
	private final String OILS_STRING = "Ulja";
	private final Short OILS_SUBCATEGORY_ID = 36;
	private final String ZVIJEZDA_STRING = "Zvijezda";
	private final String SUNFLOWER_OIL_STRING = "Suncokretovo ulje";
	private final Short SUNFLOWER_OIL_PRODUCT_ID = 1;
	private final String SUNFLOWER_OIL_SIZE_STRING = "1 l";
	private final String KONZUM_STRING = "KONZUM";
	private final String KONZUM_STORE_LOCATION_STRING = "Britanski trg 12";
	private final Short KONZUM_STORE_SPECIFIC_ID = 1;
	private final String LITER_STRING = "l";
	
	
	private final Byte ADMIN_USER_TYPE_ID = 2;
	private final String ADMIN_STRING = "admin";
	private final Short REGULAR_USER_USER_ID = 2;
	private final String REGULAR_USER_STRING = "regularUser";
	private final Short KONZUM_STORE_ID = 1;
	private final Short JURISICEVA_ULICA_13_STORE_SPECIFIC_ID = 3;
	private final String JURISICEVA_ULICA_13_ADDRESS = "Jurišićeva ulica 13";
	private final Byte LITER_PRODUCT_SIZE_ID = 4;
	private final Short ZVIJEZDA_PRODUCER_ID = 1;
	private final Short CRYSTAL_WHITE_SUGAR_PRODUCT_ID = 2;
	private final String CRYSTAL_WHITE_SUGAR_STRING = "Šećer kristal bijeli";
	private final Short SUNFLOWER_OIL_PRODUCT_SPECIFIC_ID = 1;
	private final String SUNFLOWER_OIL_BARCODE = "1234567890123";
	private final Short SUNFLOWER_OIL_PRODUCT_STORE_ID = 1;
	private final Integer SUNFLOWER_OIL_PRICE_ID = 1;
	private final Integer FIRST_RATING_INFORMATION_FEEDBACK_ID = 1;
	private final String FIRST_RATING_INFORMATION_FEEDBACK_STRING = "P";
	private final String BARCODE = "1111";
	

	@Autowired
	SimpleService simpleService;

	@Test
	public void testGetSectors() {
		ArrayList<String> sectorList = new ArrayList<>(simpleService.getSectors());
		assertTrue(sectorList.contains(SUPERMARKETS_STRING));
	}

	@Test
	public void testGetCategoriesForSectorName() {
		ArrayList<String> categoryList = new ArrayList<>(simpleService.getCategoriesForSectorName(SUPERMARKETS_STRING));
		assertTrue(categoryList.contains(MEAL_PREPARATION_STRING));
	}

	@Test
	public void testGetSubcategoriesForCategoryName() {
		ArrayList<String> subcategoryList = new ArrayList<>(
				simpleService.getSubcategoriesForCategoryName(MEAL_PREPARATION_STRING));
		assertTrue(subcategoryList.contains(OILS_STRING));
	}

	@Test
	public void testGetSubcategoryIdForCategoryAndSubcategoryName() {
		Short subcategoryId = simpleService.getSubcategoryIdForCategoryAndSubcategoryName(MEAL_PREPARATION_STRING,
				OILS_STRING);
		assertTrue(subcategoryId == OILS_SUBCATEGORY_ID);
	}

	@Test
	public void testGetProducersForSubcategoryName() {
		ArrayList<String> producerList = new ArrayList<>(simpleService.getProducersForSubcategoryName(OILS_STRING));
		assertTrue(producerList.contains(ZVIJEZDA_STRING));
	}

	@Test
	public void testGetProductNamesForSubcategoryAndProducerName() {
		ArrayList<String> productList = new ArrayList<>(
				simpleService.getProductNamesForSubcategoryAndProducerName(OILS_STRING, ZVIJEZDA_STRING));
		assertTrue(productList.contains(SUNFLOWER_OIL_STRING));
	}

	@Test
	public void testGetProductIdForProducerAndProductName() {
		Short productId = simpleService.getProductIdForProducerAndProductName(ZVIJEZDA_STRING, SUNFLOWER_OIL_STRING);
		assertTrue(productId == SUNFLOWER_OIL_PRODUCT_ID);
	}

	@Test
	public void testGetSizeValuesForProductId() {
		ArrayList<String> sizeList = new ArrayList<>(simpleService.getSizeValuesForProductId(SUNFLOWER_OIL_PRODUCT_ID));
		assertTrue(sizeList.contains(SUNFLOWER_OIL_SIZE_STRING));
	}

	@Test
	public void testGetStoreNames() {
		ArrayList<String> sizeList = new ArrayList<>(simpleService.getStoreNames());
		assertTrue(sizeList.contains(KONZUM_STRING));
	}

	@Test
	public void testGetStoreLocations() {
		ArrayList<String> storeLocationList = new ArrayList<>(simpleService.getStoreLocations(KONZUM_STRING));
		assertTrue(storeLocationList.contains(KONZUM_STORE_LOCATION_STRING));
	}

	@Test
	public void testGetStoreSpecificIdForStoreLocation() {
		Short storeSpecificId = simpleService.getStoreSpecificIdForStoreLocation(KONZUM_STORE_LOCATION_STRING);
		assertTrue(storeSpecificId == KONZUM_STORE_SPECIFIC_ID);
	}

	@Test
	public void testGetSizeTypes() {
		ArrayList<String> sizeTypeList = new ArrayList<>(simpleService.getSizeTypes());
		assertTrue(sizeTypeList.contains(LITER_STRING));
	}
	
	@Test
	public void testGetProductInformationForBarcode() {
		ProductInformation productInformation = simpleService.getProductInformationForBarcode(BARCODE);
		assertTrue(productInformation != null && productInformation.getPhotoURI().length() > 50);
	}

}
