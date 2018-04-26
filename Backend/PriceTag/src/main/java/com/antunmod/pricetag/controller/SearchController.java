package com.antunmod.pricetag.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antunmod.pricetag.model.transfer.SearchFilter;
import com.antunmod.pricetag.model.transfer.SearchProductData;
import com.antunmod.pricetag.service.SearchService;

/*
 * This is a Controller class for getting data while searching through the database.
 */
@RestController
@RequestMapping("/search")
public class SearchController {

	private SearchService searchService;
	
	@ResponseBody
	@GetMapping("")
	public ResponseEntity<List<SearchProductData>> getProducts(@RequestBody SearchFilter searchFilter) {
		ArrayList<SearchProductData> searchProductDataList = new ArrayList<>(searchService.getProducts(searchFilter));
		return new ResponseEntity<List<SearchProductData>>(searchProductDataList, HttpStatus.OK);
	}
	
}
