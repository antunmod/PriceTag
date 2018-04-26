package com.antunmod.pricetag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.transfer.SearchFilter;
import com.antunmod.pricetag.model.transfer.SearchProductData;
import com.antunmod.pricetag.repo.ProductSpecificRepository;

/*
 * This is the Service class which gets product data requested by the user.
 */
@Service
public class SearchService {

	@Autowired
	private ProductSpecificRepository productSpecificRepository;
	
	/*
	 * This method will return all products in the database.
	 */
	public List<SearchProductData> getProducts(SearchFilter searchFilter) {
		List<SearchProductData> searchProductDataList = productSpecificRepository.findProducts(
				searchFilter.getCategoryName(),
				searchFilter.getSubcategoryName(),
				searchFilter.getProducerName(),
				searchFilter.getProductName(),
				searchFilter.getStoreName());
		return searchProductDataList;
	}
	
}
