package com.antunmod.pricetag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.antunmod.pricetag.service.AddProductService;

/*
 * This is a Controller class for adding data to database.
 */
@RestController
@RequestMapping("/add")
public class AddProductController {

	@Autowired
	private AddProductService addProductService;

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: product_specific, product_store, price
	 */
	@ResponseBody
	@PostMapping("/productSpecific")
	public ResponseEntity<Boolean> addProductSpecific(@RequestBody AddProductSpecific addProductSpecific) {
		if (addProductSpecific == null)
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		Boolean success = addProductService.saveProductSpecific(addProductSpecific);
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: product, product_specific, product_store, price,
	 * subcategory_product
	 */
	@ResponseBody
	@PostMapping("/product")
	public ResponseEntity<Boolean> addProduct(@RequestBody AddProduct addProduct) {
		Boolean success = addProductService.saveProduct(addProduct);
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: producer, product, product_specific, product_store, price,
	 * subcategory_product
	 */
	@ResponseBody
	@PostMapping("/producer")
	public ResponseEntity<Boolean> addProducer(@RequestBody AddProducer addProducer) {
		Boolean success = addProductService.saveProducer(addProducer);
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: store_specific, product_specific, product_store, price
	 */
	@ResponseBody
	@PostMapping("/storeSpecificProductSpecific")
	public ResponseEntity<Boolean> addStoreSpecificProductSpecific(
			@RequestBody AddStoreSpecificProductSpecific addStoreSpecificProductSpecific) {
		Boolean success = addProductService.saveStoreSpecificProductSpecific(addStoreSpecificProductSpecific);
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: store_specific, product, product_specific, product_store, price,
	 * subcategory_product
	 */
	@ResponseBody
	@PostMapping("/storeSpecificProduct")
	public ResponseEntity<Boolean> addStoreSpecificProduct(
			@RequestBody AddStoreSpecificProduct addStoreSpecificProduct) {
		Boolean success = addProductService.saveStoreSpecificProduct(addStoreSpecificProduct);
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: store_specific, producer, product, product_specific, product_store,
	 * price, subcategory_product
	 */
	@ResponseBody
	@PostMapping("/storeSpecificProducer")
	public ResponseEntity<Boolean> addStoreSpecificProducer(
			@RequestBody AddStoreSpecificProducer addStoreSpecificProducer) {
		Boolean success = addProductService.saveStoreSpecificProducer(addStoreSpecificProducer);
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: store, store_specific, product_specific, product_store, price
	 */
	@ResponseBody
	@PostMapping("/storeProductSpecific")
	public ResponseEntity<Boolean> addStoreProductSpecific(
			@RequestBody AddStoreProductSpecific addStoreProductSpecific) {
		Boolean success = addProductService.saveStoreProductSpecific(addStoreProductSpecific);
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: store, store_specific, product, product_specific, product_store,
	 * price, subcategory_product
	 */
	@ResponseBody
	@PostMapping("/storeProduct")
	public ResponseEntity<Boolean> addStoreProduct(@RequestBody AddStoreProduct addStoreProduct) {
		Boolean success = addProductService.saveStoreProduct(addStoreProduct);
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: store, store_specific, producer, product, product_specific,
	 * product_store, price, subcategory_product
	 */
	@ResponseBody
	@PostMapping("/storeProducer")
	public ResponseEntity<Boolean> addStoreProducer(@RequestBody AddStoreProducer addStoreProducer) {
		Boolean success = addProductService.saveStoreProducer(addStoreProducer);
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: price
	 */
	@ResponseBody
	@PostMapping("/price")
	public ResponseEntity<Boolean> addPrice(@RequestBody AddPrice addPrice) {
		Boolean success = addProductService.savePrice(addPrice);
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: store_specific, product_specific, price
	 */
	@ResponseBody
	@PostMapping("/storeSpecificProductStore")
	public ResponseEntity<Boolean> addStoreSpecificProductStore(
			@RequestBody AddStoreSpecificProductStore addStoreSpecificProductStore) {
		Boolean success = addProductService.saveStoreSpecificProductStore(addStoreSpecificProductStore);
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: store, store_specific, product_specific, price
	 */
	@ResponseBody
	@PostMapping("/storeProductStore")
	public ResponseEntity<Boolean> addStoreProductStore(@RequestBody AddStoreProductStore addStoreProductStore) {
		Boolean success = addProductService.saveStoreProductStore(addStoreProductStore);
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}

}
