package com.antunmod.pricetag.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.antunmod.pricetag.PriceTagApplication;
import com.antunmod.pricetag.model.database.Category;
import com.antunmod.pricetag.model.database.CategorySubcategory;
import com.antunmod.pricetag.model.database.InformationFeedback;
import com.antunmod.pricetag.model.database.Price;
import com.antunmod.pricetag.model.database.Producer;
import com.antunmod.pricetag.model.database.Product;
import com.antunmod.pricetag.model.database.ProductSpecific;
import com.antunmod.pricetag.model.database.ProductStore;
import com.antunmod.pricetag.model.database.Sector;
import com.antunmod.pricetag.model.database.SectorCategory;
import com.antunmod.pricetag.model.database.Size;
import com.antunmod.pricetag.model.database.Store;
import com.antunmod.pricetag.model.database.StoreSpecific;
import com.antunmod.pricetag.model.database.Subcategory;
import com.antunmod.pricetag.model.database.SubcategoryProduct;
import com.antunmod.pricetag.model.database.User;
import com.antunmod.pricetag.model.database.UserType;
import com.antunmod.pricetag.repo.CategoryRepository;
import com.antunmod.pricetag.repo.CategorySubcategoryRepository;
import com.antunmod.pricetag.repo.InformationFeedbackRepository;
import com.antunmod.pricetag.repo.PriceRepository;
import com.antunmod.pricetag.repo.ProducerRepository;
import com.antunmod.pricetag.repo.ProductRepository;
import com.antunmod.pricetag.repo.ProductSpecificRepository;
import com.antunmod.pricetag.repo.ProductStoreRepository;
import com.antunmod.pricetag.repo.SectorCategoryRepository;
import com.antunmod.pricetag.repo.SectorRepository;
import com.antunmod.pricetag.repo.SizeRepository;
import com.antunmod.pricetag.repo.StoreRepository;
import com.antunmod.pricetag.repo.StoreSpecificRepository;
import com.antunmod.pricetag.repo.SubcategoryProductRepository;
import com.antunmod.pricetag.repo.SubcategoryRepository;
import com.antunmod.pricetag.repo.UserRepository;
import com.antunmod.pricetag.repo.UserTypeRepository;

/*
 * This class tests repositories to get initial data from the database.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PriceTagApplication.class)
public class RepositoryTest {

	@Autowired
	private UserTypeRepository userTypeRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategorySubcategoryRepository categorySubcategoryRepository;
	@Autowired
	private PriceRepository priceRepository;
	@Autowired
	private ProducerRepository producerRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductSpecificRepository productSpecificRepository;
	@Autowired
	private ProductStoreRepository productStoreRepository;
	@Autowired
	private SectorCategoryRepository sectorCategoryRepository;
	@Autowired
	private SectorRepository sectorRepository;
	@Autowired
	private SizeRepository sizeRepository;
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private StoreSpecificRepository storeSpecificRepository;
	@Autowired
	private SubcategoryProductRepository subcategoryProductRepository;
	@Autowired
	private SubcategoryRepository subcategoryRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private InformationFeedbackRepository informationFeedbackRepository;

	/*
	 * Final variables used for testing.
	 */
	private final Byte SUPERMARKETS_SECTOR_ID = 1;
	private final String SUPERMARKETS_STRING = "Supermarketi";
	private final Byte WINERY_CATEGORY_ID = 1;
	private final String WINERY_STRING = "Vinoteka";
	private final Short WHITE_WINES_SUBCATEGORY_ID = 2;
	private final String WHITE_WINES_STRING = "Bijela vina";
	private final Short SUGAR_AND_ARTIFICIAL_SWEETENERS_SUBCATEGORY_ID = 37;
	private final Short WHITE_CRYSTAL_SUGAR_PRODUCT_ID = 2;
	private final Byte ADMIN_USER_TYPE_ID = 2;
	private final String ADMIN_STRING = "admin";
	private final Short REGULAR_USER_USER_ID = 2;
	private final String REGULAR_USER_STRING = "regularUser";
	private final Short KONZUM_STORE_ID = 1;
	private final String KONZUM_STRING = "KONZUM";
	private final Short JURISICEVA_ULICA_13_STORE_SPECIFIC_ID = 3;
	private final String JURISICEVA_ULICA_13_ADDRESS = "Jurišićeva ulica 13";
	private final Byte LITER_PRODUCT_SIZE_ID = 4;
	private final String LITER_STRING = "l";
	private final Short ZVIJEZDA_PRODUCER_ID = 1;
	private final String ZVIJEZDA_STRING = "Zvijezda";
	private final Short CRYSTAL_SUGAR_PRODUCT_ID = 2;
	private final String CRYSTAL_SUGAR_STRING = "Šećer kristal";
	private final Short SUNFLOWER_OIL_PRODUCT_SPECIFIC_ID = 1;
	private final String SUNFLOWER_OIL_BARCODE = "3858882210010";
	private final Short SUNFLOWER_OIL_PRODUCT_STORE_ID = 1;
	private final Integer SUNFLOWER_OIL_PRICE_ID = 1;
	private final Integer FIRST_RATING_INFORMATION_FEEDBACK_ID = 1;
	private final String FIRST_RATING_INFORMATION_FEEDBACK_STRING = "P";

	@Test
	public void testUserType() {
		UserType userType = userTypeRepository.findByUserTypeId(ADMIN_USER_TYPE_ID);
		assertEquals(userType.getUserTypeDescription(), ADMIN_STRING);
	}

	@Test
	public void testUser() {
		User user = userRepository.findByUserId(REGULAR_USER_USER_ID);
		assertEquals(user.getUserName(), REGULAR_USER_STRING);
	}

	@Test
	public void testStore() {
		Store store = storeRepository.findByStoreId(KONZUM_STORE_ID);
		assertEquals(store.getStoreName(), KONZUM_STRING);
	}

	@Test
	public void testStoreSpecific() {
		StoreSpecific storeSpecific = storeSpecificRepository
				.findByStoreSpecificId(JURISICEVA_ULICA_13_STORE_SPECIFIC_ID);
		assertEquals(storeSpecific.getStoreAddress(), JURISICEVA_ULICA_13_ADDRESS);
	}

	@Test
	public void testSectorRepository() {
		Sector sector = sectorRepository.findBySectorId(SUPERMARKETS_SECTOR_ID);
		assertEquals(sector.getSectorName(), SUPERMARKETS_STRING);
	}

	@Test
	public void testCategoryRepository() {
		Category category = categoryRepository.findByCategoryId(WINERY_CATEGORY_ID);
		assertEquals(category.getCategoryName(), WINERY_STRING);
	}

	@Test
	public void testSubcategoryRepository() {
		Subcategory subcategory = subcategoryRepository.findBySubcategoryId(WHITE_WINES_SUBCATEGORY_ID);
		assertEquals(subcategory.getSubcategoryName(), WHITE_WINES_STRING);
	}

	@Test
	public void testSectorCategoryRepository() {
		SectorCategory sectorCategory = sectorCategoryRepository.findByCategoryId(WINERY_CATEGORY_ID);
		assertTrue(sectorCategory.getSectorId() == SUPERMARKETS_SECTOR_ID);
	}

	@Test
	public void testCategorySubcategoryRepository() {
		CategorySubcategory categorySubcategory = categorySubcategoryRepository
				.findBySubcategoryId(WHITE_WINES_SUBCATEGORY_ID);
		assertTrue(categorySubcategory.getCategoryId() == WINERY_CATEGORY_ID);
	}

	@Test
	public void testProductSize() {
		Size size = sizeRepository.findByProductSizeId(LITER_PRODUCT_SIZE_ID);
		assertEquals(size.getSizeType(), LITER_STRING);
	}

	@Test
	public void testProducer() {
		Producer producer = producerRepository.findByProducerId(ZVIJEZDA_PRODUCER_ID);
		assertEquals(producer.getProducerName(), ZVIJEZDA_STRING);
	}

	@Test
	public void testProduct() {
		Product product = productRepository.findByProductId(CRYSTAL_SUGAR_PRODUCT_ID);
		assertEquals(product.getProductName(), CRYSTAL_SUGAR_STRING);
	}

	@Test
	public void testProductSpecific() {
		ProductSpecific productSpecific = productSpecificRepository
				.findByProductSpecificId(SUNFLOWER_OIL_PRODUCT_SPECIFIC_ID);
		assertEquals(productSpecific.getBarcode(), SUNFLOWER_OIL_BARCODE);
	}

	@Test
	public void testProductStore() {
		ProductStore productStore = productStoreRepository.findByProductStoreId(SUNFLOWER_OIL_PRODUCT_STORE_ID);
		assertTrue(productStore.getProductSpecificId() == SUNFLOWER_OIL_PRODUCT_SPECIFIC_ID);
	}

	@Test
	public void testPrice() {
		Price price = priceRepository.findByPriceId(SUNFLOWER_OIL_PRICE_ID);
		assertTrue(price.getProductStoreId() == SUNFLOWER_OIL_PRODUCT_STORE_ID);
	}

	@Test
	public void testSubcategoryProductRepository() {
		SubcategoryProduct subcategoryProduct = subcategoryProductRepository
				.findByProductId(WHITE_CRYSTAL_SUGAR_PRODUCT_ID);
		assertTrue(subcategoryProduct.getSubcategoryId() == SUGAR_AND_ARTIFICIAL_SWEETENERS_SUBCATEGORY_ID);
	}

	@Test
	public void testInformationFeedback() {
		InformationFeedback informationFeedback = informationFeedbackRepository.findByInformationFeedbackId(FIRST_RATING_INFORMATION_FEEDBACK_ID);
		assertEquals(informationFeedback.getFeedback(), FIRST_RATING_INFORMATION_FEEDBACK_STRING);
	}
	
}
