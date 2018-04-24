package com.antunmod.pricetag.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.Store;
import com.antunmod.pricetag.repo.CategoryRepository;
import com.antunmod.pricetag.repo.ProductRepository;
import com.antunmod.pricetag.repo.SectorRepository;
import com.antunmod.pricetag.repo.SizeRepository;
import com.antunmod.pricetag.repo.StoreRepository;
import com.antunmod.pricetag.repo.SubcategoryRepository;

/*
 * This is the Service class which is used with SimpleController for simple GET requests.
 */
@Service
public class SimpleService {

	private final Short NOT_FOUND_SHORT = -1;

	@Autowired
	private SectorRepository sectorRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubcategoryRepository subcategoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private SizeRepository sizeRepository;

	public List<String> getSectors() {
		List<String> sectorList = sectorRepository.getAllSectorNames();
		if (sectorList != null) {
			return sectorList;
		}
		return new ArrayList<String>();
	}

	public List<String> getCategoriesForSectorName(String sectorName) {
		List<String> categoryList = categoryRepository.findAllForSectorName(sectorName);
		if (categoryList != null) {
			return categoryList;

		}
		return new ArrayList<String>();
	}

	public List<String> getSubcategoriesForCategoryName(String categoryName) {
		List<String> subcategoryList = subcategoryRepository.findAllForCategoryName(categoryName);
		if (subcategoryList != null) {
			return subcategoryList;

		}
		return new ArrayList<String>();
	}

	public Short getSubcategoryIdForCategoryAndSubcategoryName(String categoryName, String subcategoryName) {
		Short subcategoryId = subcategoryRepository.findSubcategoryIdForCategoryAndSubcategoryName(categoryName,
				subcategoryName);
		if (subcategoryId != null) {
			return subcategoryId;

		}
		return NOT_FOUND_SHORT;
	}

	public List<String> getProducersForSubcategoryName(String subcategoryName) {
		List<String> producerList = productRepository.findAllForSubcategoryName(subcategoryName);
		if (producerList != null) {
			return producerList;

		}
		return new ArrayList<String>();
	}

	public List<String> getProductNamesForSubcategoryAndProducerName(String subcategoryName, String producerName) {
		List<String> productList = productRepository.getProductNamesForSubcategoryNameAndProducer(subcategoryName,
				producerName);
		if (productList != null) {
			return productList;

		}
		return new ArrayList<String>();
	}

	public Short getProductIdForProducerAndProductName(String producerName, String productName) {
		Short productId = productRepository.getProductIdForProducerAndProductName(producerName, productName);
		if (productId != null) {
			return productId;

		}
		return NOT_FOUND_SHORT;
	}

	public List<String> getSizeValuesForProductId(Short productId) {
		List<String> sizeValues = productRepository.getSizeValuesForProductId(productId);
		if (sizeValues != null) {
			return sizeValues;
		}
		return new ArrayList<String>();
	}

	public List<String> getStoreNames() {
		List<Store> storeList = storeRepository.findAll();
		if (storeList == null) {
			return new ArrayList<>();
		}
		List<String> storeStringList = new ArrayList<>();
		for (Integer i = 0; i < storeList.size(); ++i) {
			String storeName = storeList.get(i).getStoreName();
			if (!storeStringList.contains(storeName)) {
				storeStringList.add(storeName);
			}
		}

		return storeStringList;
	}

	public List<String> getStoreLocations(String storeName) {
		List<String> storeLocationList = storeRepository.getStoreLocations(storeName);
		if (!storeLocationList.isEmpty()) {
			return storeLocationList;
		}
		return new ArrayList<String>();
	}

	public Short getStoreSpecificIdForStoreLocation(String storeLocation) {
		Short storeId = storeRepository.findStoreIdForStoreAddress(storeLocation);
		if (storeId != null) {
			return storeId;
		}
		return NOT_FOUND_SHORT;

	}

	public List<String> getSizeTypes() {
		List<String> sizeTypeList = sizeRepository.findSizeTypes();
		if (sizeTypeList != null)
			return sizeTypeList;
		return new ArrayList<>();
	}

}
