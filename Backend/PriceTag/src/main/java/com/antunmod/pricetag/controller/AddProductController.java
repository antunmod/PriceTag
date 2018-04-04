package com.antunmod.pricetag.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antunmod.pricetag.model.transfer.AddPrice;
import com.antunmod.pricetag.model.transfer.AddProducer;
import com.antunmod.pricetag.model.transfer.AddProduct;
import com.antunmod.pricetag.model.transfer.AddProductSpecific;
import com.antunmod.pricetag.model.transfer.AddStoreProducer;
import com.antunmod.pricetag.model.transfer.AddStoreProduct;
import com.antunmod.pricetag.model.transfer.AddStoreProductSpecific;
import com.antunmod.pricetag.model.transfer.AddStoreProductStore;
import com.antunmod.pricetag.model.transfer.AddStoreSpecificProducer;
import com.antunmod.pricetag.model.transfer.AddStoreSpecificProduct;
import com.antunmod.pricetag.model.transfer.AddStoreSpecificProductSpecific;
import com.antunmod.pricetag.model.transfer.AddStoreSpecificProductStore;

/*
 * This is a Controller class for adding data to database.
 */
@RestController
@RequestMapping("/add")
public class AddProductController {

	/*
	 * This mapping will result in addition of entries to the following tables in database:
	 * 		- product_specific
	 * 		- product_store
	 * 		- price
	 */
	@ResponseBody
	@PostMapping("/productSpecific")
	public ResponseEntity<Boolean> addProductSpecific(@RequestBody AddProductSpecific addProductSpecific) {
		return new saveProductSpecific(addProductSpecific);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in database:
	 * 		- product
	 * ¸	- product_specific
	 * 		- product_store
	 * 		- price
	 * 		- subcategory_product
	 */
	@ResponseBody
	@PostMapping("/product")
	public ResponseEntity<Boolean> addProduct(@RequestBody AddProduct addProduct) {
		return new saveProduct(addProduct);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in database:
	 * 		- producer
	 * 		- product
	 * ¸	- product_specific
	 * 		- product_store
	 * 		- price
	 * 		- subcategory_product
	 */
	@ResponseBody
	@PostMapping("/producer")
	public ResponseEntity<Boolean> addProducer(@RequestBody AddProducer addProducer) {
		return new saveProducer(addProducer);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in database:
	 * 		- store_specific
	 * 		- product_specific
	 * 		- product_store
	 * 		- price
	 */
	@ResponseBody
	@PostMapping("/storeSpecificProductSpecific")
	public ResponseEntity<Boolean> addStoreSpecificProductSpecific(
			@RequestBody AddStoreSpecificProductSpecific addStoreSpecificProductSpecific) {
		return new saveStoreSpecificProductSpecific(addStoreSpecificProductSpecific);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in database:
	 * 		- store_specific
	 * 		- product
	 * 		- product_specific
	 * 		- product_store
	 * 		- price
	 * 		- subcategory_product
	 */
	@ResponseBody
	@PostMapping("/storeSpecificProduct")
	public ResponseEntity<Boolean> addStoreSpecificProduct(
			@RequestBody AddStoreSpecificProduct addStoreSpecificProduct) {
		return new saveStoreSpecificProduct(addStoreSpecificProduct);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in database:
	 * 		- store_specific
	 * 		- producer
	 * 		- product
	 * 		- product_specific
	 * 		- product_store
	 * 		- price
	 * 		- subcategory_product
	 */
	@ResponseBody
	@PostMapping("/storeSpecificProducer")
	public ResponseEntity<Boolean> addStoreSpecificProducer(
			@RequestBody AddStoreSpecificProducer addStoreSpecificProducer) {
		return new saveStoreSpecificProducer(addStoreSpecificProducer);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in database:
	 * 		- store
	 * 		- store_specific
	 * 		- product_specific
	 * 		- product_store
	 * 		- price
	 */
	@ResponseBody
	@PostMapping("/storeProductSpecific")
	public ResponseEntity<Boolean> addStoreProductSpecific(@RequestBody AddStoreProductSpecific addStoreProductSpecific) {
		return new saveStoreProductSpecific(addStoreProductSpecific);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in database:
	 * 		- store
	 * 		- store_specific
	 * 		- product
	 * 		- product_specific
	 * 		- product_store
	 * 		- price
	 * 		- subcategory_product
	 */
	@ResponseBody
	@PostMapping("/storeProduct")
	public ResponseEntity<Boolean> addStoreProduct(@RequestBody AddStoreProduct addStoreProduct) {
		return new saveStoreProduct(addStoreProduct);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in database:
	 * 		- store
	 * 		- store_specific
	 * 		- producer
	 * 		- product
	 * 		- product_specific
	 * 		- product_store
	 * 		- price
	 * 		- subcategory_product
	 */
	@ResponseBody
	@PostMapping("/storeProducer")
	public ResponseEntity<Boolean> addStoreProducer(@RequestBody AddStoreProducer addStoreProducer) {
		return new saveStoreProducer(addStoreProducer);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in database:
	 * 		- price
	 */
	@ResponseBody
	@PostMapping("/price")
	public ResponseEntity<Boolean> addPrice(@RequestBody AddPrice addPrice) {
		return new savePrice(addPrice);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in database:
	 * 		- store_specific
	 * 		- product_specific
	 * 		- price
	 */
	@ResponseBody
	@PostMapping("/storeSpecificProductStore")
	public ResponseEntity<Boolean> addStoreSpecificProductStore(@RequestBody AddStoreSpecificProductStore addStoreSpecificProductStore) {
		return new saveStoreSpecificProductStore(addStoreSpecificProductStore);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in database:
	 * 		- store
	 * 		- store_specific
	 * 		- product_specific
	 * 		- price
	 */
	@ResponseBody
	@PostMapping("/storeProductStore")
	public ResponseEntity<Boolean> addStoreProductStore(@RequestBody AddStoreProductStore addStoreProductStore) {
		return new saveStoreProductStore(addStoreProductStore);
	}

}
