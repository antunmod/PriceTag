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
import com.antunmod.pricetag.repo.CategoryRepository;
import com.antunmod.pricetag.repo.CategorySubcategoryRepository;
import com.antunmod.pricetag.repo.SectorCategoryRepository;
import com.antunmod.pricetag.repo.SectorRepository;
import com.antunmod.pricetag.repo.SizeRepository;
import com.antunmod.pricetag.repo.StoreRepository;
import com.antunmod.pricetag.repo.SubcategoryProductRepository;
import com.antunmod.pricetag.repo.SubcategoryRepository;
import com.antunmod.pricetag.repo.SuggestedCategorizationRepository;

@RestController
public class SimpleController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategorySubcategoryRepository categorySubcategoryRepository;
	
	@Autowired
	private SectorRepository sectorRepository;
	
	@Autowired
	private SectorCategoryRepository sectorCategoryRepository;
	
	@Autowired
	private SizeRepository sizeRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private SubcategoryRepository subcategoryRepository;
	
	@Autowired
	private SubcategoryProductRepository subcategoryProductRepository;
	
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
	public ResponseEntity<List<String>> getSectors() {
		List<Sector> sectorList = sectorRepository.findAll();
		List<String> sectorStringList = new ArrayList<String>();
		for(Sector sector: sectorList) {
			sectorStringList.add(sector.getSectorName());
		}
		return new ResponseEntity<List<String>>(sectorStringList, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/categories")
	public ResponseEntity<List<String>> getCategoriesForSector(@RequestParam("sectorName") String sectorName) {
		List<Sector> sectorList = sectorRepository.findAll();
		List<String> sectorStringList = new ArrayList<String>();
		for(Sector sector: sectorList) {
			sectorStringList.add(sector.getSectorName());
		}
		return new ResponseEntity<List<String>>(sectorStringList, HttpStatus.OK);
	}
	
}
