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

	private final Short ADDING_PRODUCTS_FAILED = -1;
	private Short productSpecificId = ADDING_PRODUCTS_FAILED;

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: product_specific, product_store, price
	 */
	@ResponseBody
	@PostMapping("/productSpecific")
	public ResponseEntity<Short> addProductSpecific(@RequestBody AddProductSpecific addProductSpecific) {
		Boolean success = addProductService.saveProductSpecific(addProductSpecific);
		if (success) {
			productSpecificId = addProductService.getProductSpecificIdForBarcode(addProductSpecific.getBaseProduct().getBarcode());
		}
		return new ResponseEntity<Short>(productSpecificId, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: product, product_specific, product_store, price,
	 * subcategory_product
	 */
	@ResponseBody
	@PostMapping("/product")
	public ResponseEntity<Short> addProduct(@RequestBody AddProduct addProduct) {
		Boolean success = addProductService.saveProduct(addProduct);
		if (success) {
			productSpecificId = addProductService.getProductSpecificIdForBarcode(addProduct.getBaseProduct().getBarcode());
		}
		return new ResponseEntity<Short>(productSpecificId, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: producer, product, product_specific, product_store, price,
	 * subcategory_product
	 */
	@ResponseBody
	@PostMapping("/producer")
	public ResponseEntity<Short> addProducer(@RequestBody AddProducer addProducer) {
		Boolean success = addProductService.saveProducer(addProducer);
		return new ResponseEntity<Short>(productSpecificId, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: store_specific, product_specific, product_store, price
	 */
	@ResponseBody
	@PostMapping("/storeSpecificProductSpecific")
	public ResponseEntity<Short> addStoreSpecificProductSpecific(
			@RequestBody AddStoreSpecificProductSpecific addStoreSpecificProductSpecific) {
		Boolean success = addProductService.saveStoreSpecificProductSpecific(addStoreSpecificProductSpecific);
		return new ResponseEntity<Short>(productSpecificId, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: store_specific, product, product_specific, product_store, price,
	 * subcategory_product
	 */
	@ResponseBody
	@PostMapping("/storeSpecificProduct")
	public ResponseEntity<Short> addStoreSpecificProduct(
			@RequestBody AddStoreSpecificProduct addStoreSpecificProduct) {
		Boolean success = addProductService.saveStoreSpecificProduct(addStoreSpecificProduct);
		return new ResponseEntity<Short>(productSpecificId, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: store_specific, producer, product, product_specific, product_store,
	 * price, subcategory_product
	 */
	@ResponseBody
	@PostMapping("/storeSpecificProducer")
	public ResponseEntity<Short> addStoreSpecificProducer(
			@RequestBody AddStoreSpecificProducer addStoreSpecificProducer) {
		Boolean success = addProductService.saveStoreSpecificProducer(addStoreSpecificProducer);
		return new ResponseEntity<Short>(productSpecificId, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: store, store_specific, product_specific, product_store, price
	 */
	@ResponseBody
	@PostMapping("/storeProductSpecific")
	public ResponseEntity<Short> addStoreProductSpecific(
			@RequestBody AddStoreProductSpecific addStoreProductSpecific) {
		Boolean success = addProductService.saveStoreProductSpecific(addStoreProductSpecific);
		return new ResponseEntity<Short>(productSpecificId, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: store, store_specific, product, product_specific, product_store,
	 * price, subcategory_product
	 */
	@ResponseBody
	@PostMapping("/storeProduct")
	public ResponseEntity<Short> addStoreProduct(@RequestBody AddStoreProduct addStoreProduct) {
		Boolean success = addProductService.saveStoreProduct(addStoreProduct);
		return new ResponseEntity<Short>(productSpecificId, HttpStatus.OK);
	}

	/*
	 * This mapping will result in addition of entries to the following tables in
	 * database: store, store_specific, producer, product, product_specific,
	 * product_store, price, subcategory_product
	 */
	@ResponseBody
	@PostMapping("/storeProducer")
	public ResponseEntity<Short> addStoreProducer(@RequestBody AddStoreProducer addStoreProducer) {
		Boolean success = addProductService.saveStoreProducer(addStoreProducer);
		return new ResponseEntity<Short>(productSpecificId, HttpStatus.OK);
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
