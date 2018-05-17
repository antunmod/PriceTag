package com.antunmod.pricetag.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antunmod.pricetag.model.transfer.SearchFilter;
import com.antunmod.pricetag.model.transfer.SearchProductData;
import com.antunmod.pricetag.model.transfer.StoreProductPrice;
import com.antunmod.pricetag.service.SearchService;

/*
 * This is a Controller class for getting data while searching through the database.
 */
@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@ResponseBody
	@PostMapping("")
	public ResponseEntity<ArrayList<SearchProductData>> getProducts(@RequestBody SearchFilter searchFilter) {
		ArrayList<SearchProductData> searchProductDataList = searchService.getProducts(searchFilter);
		return new ResponseEntity<ArrayList<SearchProductData>>(searchProductDataList, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/locations")
	public ResponseEntity<ArrayList<StoreProductPrice>> getLocationsForProductSpecificId(@RequestParam ("productSpecificId") Short productSpecificId) {
		ArrayList<StoreProductPrice> storeProductPricelist = searchService.getLocationsForProductSpecificId(productSpecificId);
		return new ResponseEntity<ArrayList<StoreProductPrice>>(storeProductPricelist, HttpStatus.OK);
	}
	
}
