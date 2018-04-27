package com.antunmod.pricetag.service;

import java.util.ArrayList;
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
	public ArrayList<SearchProductData> getProducts(SearchFilter searchFilter) {
		List<Object[]> objectArrayList = productSpecificRepository.findProducts(
				searchFilter.getCategoryName(),
				searchFilter.getSubcategoryName(),
				searchFilter.getProducerName(),
				searchFilter.getProductName(),
				searchFilter.getStoreName());
		ArrayList<SearchProductData> searchProductDataList = new ArrayList<>();
		for (Object[] o : objectArrayList) {
			searchProductDataList.add(new SearchProductData(
					(Short)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4], (String)o[5]));
		}
		return searchProductDataList;
	}
	
}
