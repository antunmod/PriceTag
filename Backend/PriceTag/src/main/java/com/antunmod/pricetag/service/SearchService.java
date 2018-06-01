package com.antunmod.pricetag.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.transfer.SearchFilter;
import com.antunmod.pricetag.model.transfer.SearchProductData;
import com.antunmod.pricetag.model.transfer.StoreProductPrice;
import com.antunmod.pricetag.repo.PriceRepository;
import com.antunmod.pricetag.repo.ProductSpecificRepository;

/*
 * This is the Service class which gets product data requested by the user.
 */
@Service
public class SearchService {

	@Autowired
	private ProductSpecificRepository productSpecificRepository;
	
	@Autowired
	private PriceRepository priceRepository;
	
	/*
	 * This method will return all products in the database for the given filter.
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
	
	/*
	 * This method will return most recent prices and user ratings for each specific store containing product 
	 * with the given productSpecificId
	 */
	public ArrayList<StoreProductPrice> getLocationsForProductSpecificId(Short productSpecificId) {
		List<Object[]> objectArrayList = priceRepository.findLocationsForProductSpecificId(productSpecificId);
		ArrayList<StoreProductPrice> storeProductPriceList = new ArrayList<>();

		
		for (Object[] o : objectArrayList) {
			storeProductPriceList.add(new StoreProductPrice(
					(Integer) o[0], (Short)o[1], (String)o[2], (String)o[3], (String)o[4].toString(), (String)o[5]));
		}
		return storeProductPriceList;
	}
	
	/*
	 * This method will return all products in the database for the given filter.
	 */
	public ArrayList<SearchProductData> getRecentProducts() {
		List<Object[]> objectArrayList = productSpecificRepository.findRecentProducts();
		ArrayList<SearchProductData> searchProductDataList = new ArrayList<>();
		for (Object[] o : objectArrayList) {
			searchProductDataList.add(new SearchProductData(
					(Short)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4], (String)o[5]));
		}
		return searchProductDataList;
	}
	
}
