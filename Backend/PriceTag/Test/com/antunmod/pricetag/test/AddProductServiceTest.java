package com.antunmod.pricetag.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.antunmod.pricetag.PriceTagApplication;
import com.antunmod.pricetag.model.ProductData;
import com.antunmod.pricetag.model.transfer.BaseProduct;
import com.antunmod.pricetag.service.AddProductService;
import com.antunmod.pricetag.service.DeleteProductService;

/*
 * This class tests adding a new product to database by AddProductService.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PriceTagApplication.class)
public class AddProductServiceTest {

	ProductData productData;
	@Autowired
	AddProductService addProductService;
	@Autowired
	DeleteProductService deleteProductService;
	
	/*
	 * The following method will populate ProductData object for the tests to use.
	 * Dummy product is Quattro joy 1650 ml, with a price of 29,99kn.
	 * Its producer is Ledo.
	 * The new store which will be added to database is Diona at address 
	 * Trg bana Josipa Jelačića 1.
	 */
	@Before
	public void setupProductData() {
		String barcode = "1111111111111";
		Short userId = 2;
		Float price = 29.99f;
		String productDescription = "";
		String photoURI = "dummy/test/photo/URI";
		Float productSize = 1.65f;
		String productSizeString = "l";
		BaseProduct baseProduct = new BaseProduct(barcode, userId, price, productDescription, 
				photoURI, productSize, productSizeString);
		Short productSpecificId = 0;
	    Short productId = 0;
	    String productName = "Quattro joy";
	    Short producerId = 0;
	    String producerName = "Ledo";
	    Short storeSpecificId = 1;
	    String storeName = "DIONA";
	    String storeAddress = "Trg bana Josipa Jelačića 1";
	    Short storeId = 1;
	    Short subcategoryId = 91;

	    productData = new ProductData(baseProduct, productSpecificId, productId, productName, producerId,
				producerName, storeSpecificId, storeName, storeAddress, storeId, subcategoryId);
	}
	
	/*
	 * Test adding data in the following tables: producer, product, product_specific, subcategory_product, 
	 * product_specific, product_store and price
	 */
	@Test
	public void testAddProducer() {
		Boolean success = addProductService.saveProducer(productData.toAddProducer());
	    deleteProductService.setProductData(productData);
		if(success) {
			success = deleteProductService.deleteProducer();
		}
		assertTrue(success);
	}
	
	/*
	 * Test adding data in the following tables: store_specific, producer, product, product_specific, subcategory_product, 
	 * product_specific, product_store and price
	 */
	@Test
	public void testAddStoreSpecificProducer() {
		Boolean success = addProductService.saveStoreSpecificProducer(productData.toAddStoreSpecificProducer());
	    deleteProductService.setProductData(productData);
		if(success) {
			success = deleteProductService.deleteStoreSpecificProducer();
		}
		assertTrue(success);
	}
	
	/*
	 * Test adding data in the following tables: store, store_specific, producer, product, product_specific, subcategory_product, 
	 * product_specific, product_store and price
	 */
	@Test
	public void testAddStoreProducer() {
		Boolean success = addProductService.saveStoreProducer(productData.toAddStoreProducer());
	    deleteProductService.setProductData(productData);
		if(success) {
			success = deleteProductService.deleteStoreProducer();
		}
		assertTrue(success);
	}
	
}
