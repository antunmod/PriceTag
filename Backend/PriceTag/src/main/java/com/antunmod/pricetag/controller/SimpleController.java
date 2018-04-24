package com.antunmod.pricetag.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antunmod.pricetag.service.SimpleService;

/*
 * This is a Controller class for getting data while adding a new product.
 */
@RestController
public class SimpleController {

	private SimpleService simpleService;

	@ResponseBody
	@GetMapping("/sectors")
	public ResponseEntity<List<String>> getSectors() {
		List<String> sectorList = simpleService.getSectors();
		return new ResponseEntity<List<String>>(sectorList, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/categories")
	public ResponseEntity<List<String>> getCategoriesForSectorName(@RequestParam("sectorName") String sectorName) {
		List<String> categoryList = simpleService.getCategoriesForSectorName(sectorName);
		return new ResponseEntity<List<String>>(categoryList, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/subcategories")
	public ResponseEntity<List<String>> getSubcategoriesForCategoryName(
			@RequestParam("categoryName") String categoryName) {
		List<String> subcategoryList = simpleService.getSubcategoriesForCategoryName(categoryName);
		return new ResponseEntity<List<String>>(subcategoryList, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/subcategories/id")
	public ResponseEntity<Integer> getSubcategoryIdForCategoryAndSubcategoryName(
			@RequestParam("categoryName") String categoryName,
			@RequestParam("subcategoryName") String subcategoryName) {
		Integer subcategoryId = simpleService.getSubcategoryIdForCategoryAndSubcategoryName(categoryName,
				subcategoryName);
		return new ResponseEntity<Integer>(subcategoryId, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/producers")
	public ResponseEntity<List<String>> getProducersForSubcategoryName(
			@RequestParam("subcategoryName") String subcategoryName) {
		List<String> producerList = simpleService.getProducersForSubcategoryName(subcategoryName);
		return new ResponseEntity<List<String>>(producerList, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/products/productNames")
	public ResponseEntity<List<String>> getProductNamesForSubcategoryNameAndProducer(
			@RequestParam("subcategoryName") String subcategoryName, @RequestParam("producer") String producer) {
		List<String> productList = simpleService.getProductNamesForSubcategoryAndProducerName(subcategoryName,
				producer);
		return new ResponseEntity<List<String>>(productList, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/products/productId")
	public ResponseEntity<Integer> getProductIdForProducerAndProductName(@RequestParam("producer") String producer,
			@RequestParam("productName") String productName) {
		Integer productId = simpleService.getProductIdForProducerAndProductName(producer, productName);
		return new ResponseEntity<Integer>(productId, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/sizes/sizeValue")
	public ResponseEntity<List<String>> getSizeValuesForProductId(@RequestParam("productId") Short productId) {
		List<String> sizeValues = simpleService.getSizeValuesForProductId(productId);
		return new ResponseEntity<List<String>>(sizeValues, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/stores")
	public ResponseEntity<List<String>> getStoreNames() {
		List<String> storeList = simpleService.getStoreNames();
		return new ResponseEntity<List<String>>(storeList, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/stores/locations")
	public ResponseEntity<List<String>> getStoreLocations(@RequestParam("storeName") String storeName) {
		List<String> storeLocationsList = simpleService.getStoreLocations(storeName);
		return new ResponseEntity<List<String>>(storeLocationsList, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/stores/address")
	public ResponseEntity<Integer> getStoreIdForAddress(@RequestParam("storeAddress") String storeAddress) {
		Integer storeId = simpleService.getStoreSpecificIdForStoreLocation(storeAddress);
		return new ResponseEntity<Integer>(storeId, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/sizes")
	public ResponseEntity<List<String>> getSizeValues() {
		List<String> sizeTypeList = simpleService.getSizeTypes();
		return new ResponseEntity<List<String>>(sizeTypeList, HttpStatus.OK);
	}

}
