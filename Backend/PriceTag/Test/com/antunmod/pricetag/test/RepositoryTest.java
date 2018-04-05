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
import com.antunmod.pricetag.model.database.Sector;
import com.antunmod.pricetag.model.database.SectorCategory;
import com.antunmod.pricetag.model.database.Subcategory;
import com.antunmod.pricetag.model.database.SubcategoryProduct;
import com.antunmod.pricetag.repo.CategoryRepository;
import com.antunmod.pricetag.repo.CategorySubcategoryRepository;
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

/*
 * This class tests repositories to get initial data from the database.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@DataJpaTest
@SpringBootTest(classes = PriceTagApplication.class)
//@ContextConfiguration({"classpath*:spring/applicationContext*.xml"})
public class RepositoryTest {
	
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
	
	private static final Byte SUPERMARKETS_SECTOR_ID = 1;
	private static final String SUPERMARKETS_NAME = "Supermarketi";
	private static final Byte WINERY_CATEGORY_ID = 1;
	private static final String WINERY_NAME = "Vinoteka";
	private static final Short WHITE_WINES_SUBCATEGORY_ID = 2;
	private static final String WHITE_WINES_NAME = "Bijela vina";
	private static final Short SUGAR_AND_ARTIFICIAL_SWEETENERS_SUBCATEGORY_ID = 37;
	private static final Short WHITE_CRYSTAL_SUGAR_PRODUCT_ID = 2;
	
	@Test
	public void testCategoryRepository() {
		Category category = categoryRepository.findByCategoryId(WINERY_CATEGORY_ID);
		assertEquals(category.getCategoryName(), WINERY_NAME);
	}

	@Test
	public void testSectorRepository() {
		Sector sector = sectorRepository.findBySectorId(SUPERMARKETS_SECTOR_ID);
		assertEquals(sector.getSectorName(), SUPERMARKETS_NAME);
	}

	@Test
	public void testSubcategoryRepository() {
		Subcategory subcategory = subcategoryRepository.findBySubcategoryId(WHITE_WINES_SUBCATEGORY_ID);
		assertEquals(subcategory.getSubcategoryName(), WHITE_WINES_NAME);
	}

	@Test
	public void testSubcategoryProductRepository() {
		SubcategoryProduct subcategoryProduct = subcategoryProductRepository.findByProductId(WHITE_CRYSTAL_SUGAR_PRODUCT_ID);
		assertTrue(subcategoryProduct.getSubcategoryId() == SUGAR_AND_ARTIFICIAL_SWEETENERS_SUBCATEGORY_ID);
	}

	@Test
	public void testCategorySubcategoryRepository() {
		CategorySubcategory categorySubcategory = categorySubcategoryRepository.findBySubcategoryId(WHITE_WINES_SUBCATEGORY_ID);
		assertTrue(categorySubcategory.getCategoryId() == WINERY_CATEGORY_ID);
	}

	@Test
	public void testSectorCategoryRepository() {
		SectorCategory sectorCategory = sectorCategoryRepository.findByCategoryId(WINERY_CATEGORY_ID);
		assertTrue(sectorCategory.getSectorId() == SUPERMARKETS_SECTOR_ID);
	}

}
