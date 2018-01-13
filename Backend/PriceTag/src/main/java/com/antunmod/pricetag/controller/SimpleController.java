package com.antunmod.pricetag.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antunmod.pricetag.model.Sector;
import com.antunmod.pricetag.model.Size;
import com.antunmod.pricetag.model.Store;
import com.antunmod.pricetag.repo.CategoryRepository;
import com.antunmod.pricetag.repo.CategorySubcategoryRepository;
import com.antunmod.pricetag.repo.ProductRepository;
import com.antunmod.pricetag.repo.ProductStoreRepository;
import com.antunmod.pricetag.repo.SectorCategoryRepository;
import com.antunmod.pricetag.repo.SectorRepository;
import com.antunmod.pricetag.repo.SizeRepository;
import com.antunmod.pricetag.repo.StoreRepository;
import com.antunmod.pricetag.repo.SubcategoryProductRepository;
import com.antunmod.pricetag.repo.SubcategoryRepository;
import com.antunmod.pricetag.repo.SuggestedCategorizationRepository;

@RestController
public class SimpleController {

	private final Integer NOT_FOUND_INTEGER = -1;
	
	@Autowired
	private SectorRepository sectorRepository;
	
	@Autowired
	private SectorCategoryRepository sectorCategoryRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategorySubcategoryRepository categorySubcategoryRepository;
	
	@Autowired
	private SubcategoryRepository subcategoryRepository;
	
	@Autowired
	private SubcategoryProductRepository subcategoryProductRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductStoreRepository productStoreRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private SizeRepository sizeRepository;
	
	@Autowired
	private SuggestedCategorizationRepository suggestedCategorizationRepository;
	
	@ResponseBody
	@GetMapping("/sizes")
	public ResponseEntity<List<String>> getSizeValues() {
		List<Size> sizes = sizeRepository.findAll();
		List<String> sizeList = new ArrayList<String>();
		for(Size size: sizes) {
			sizeList.add(size.getSizeType());
		}
		return new ResponseEntity<List<String>>(sizeList, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/sectors")
	public ResponseEntity<List<Sector>> getSectors() {
		List<Sector> sectorList = new ArrayList<Sector>();
		sectorList = sectorRepository.findAll();
		return new ResponseEntity<List<Sector>>(sectorList, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/categories")
	public ResponseEntity<List<String>> getCategoriesForSectorName(@RequestParam("sectorName") String sectorName) {
		List<String> categoryList = categoryRepository.findAllForSectorName(sectorName);
		if(categoryList!=null) {
			return new ResponseEntity<List<String>>(categoryList, HttpStatus.OK);

		}
		return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/subcategories")
	public ResponseEntity<List<String>> getSubcategoriesForCategoryName(@RequestParam("categoryName") String categoryName) {
		List<String> subcategoryList = subcategoryRepository.findAllForCategoryName(categoryName);
		if(subcategoryList!=null) {
			return new ResponseEntity<List<String>>(subcategoryList, HttpStatus.OK);

		}
		return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/producers")
	public ResponseEntity<List<String>> getProducersForSubcategoryName(@RequestParam("subcategoryName") String subcategoryName) {
		List<String> producerList = productRepository.findAllForSubcategoryName(subcategoryName);
		if(producerList!=null) {
			return new ResponseEntity<List<String>>(producerList, HttpStatus.OK);

		}
		return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/products/productNames")
	public ResponseEntity<List<String>> getProductNamesForSubcategoryNameAndProducer(
																@RequestParam("subcategoryName") String subcategoryName,
																@RequestParam("producer") String producer) {
		List<String> productList = productRepository.getProductNamesForSubcategoryNameAndProducer(subcategoryName, producer);
		if(productList==null) {
			return new ResponseEntity<List<String>>(productList, HttpStatus.OK);

		}
		return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.OK);
	}
	
	
	@ResponseBody
	@GetMapping("/stores")
	public ResponseEntity<List<String>> getStoreNames() {
		List<Store> storeList = storeRepository.findAll();
		if(storeList==null) {
			return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.OK);
		}
		List<String> storeStringList = new ArrayList<>();
		for (int i = 0; i<storeList.size(); ++i) {
			String storeName = storeList.get(i).getStoreName();
			if(!storeStringList.contains(storeName)) {
				storeStringList.add(storeName);
			}
		}
		
		return new ResponseEntity<List<String>>(storeStringList, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/stores/locations")
	public ResponseEntity<List<String>> getStoreLocations(@RequestParam("storeName") String storeName) {
		List<String> storeLocationsList = storeRepository.getStoreLocations(storeName);
		if(storeLocationsList.isEmpty()) {
			return new ResponseEntity<List<String>> (new ArrayList<String>(), HttpStatus.OK);
		}
		return new ResponseEntity<List<String>> (storeLocationsList, HttpStatus.OK);
		
		
	}
	
	@ResponseBody
	@GetMapping("/stores/address")
	public ResponseEntity<Integer> getStoreIdForAddress(@RequestParam("storeAddress") String storeAddress) {
		Integer storeId = storeRepository.findStoreIdForStoreAddress(storeAddress);
		if(storeId==null) {
			return new ResponseEntity<Integer> (NOT_FOUND_INTEGER, HttpStatus.OK);
		}
		return new ResponseEntity<Integer> (storeId, HttpStatus.OK);
		
		
	}
	
	
}
