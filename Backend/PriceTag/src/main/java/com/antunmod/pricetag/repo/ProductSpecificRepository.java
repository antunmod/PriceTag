package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.antunmod.pricetag.model.database.ProductSpecific;
import com.antunmod.pricetag.model.transfer.SearchProductData;

public interface ProductSpecificRepository extends JpaRepository<ProductSpecific, Short>{

	ProductSpecific findByProductSpecificId(Short productSpecificId);
	
	ProductSpecific findByBarcode(String barcode);
	
	@Query(value = "SELECT product_specific_ID, " + 
			"photo_URI, " + 
			"producer_name, " + 
			"product_name, " + 
			"product_description, " + 
			"CONCAT(TRIM(TRAILING '.' FROM TRIM(TRAILING '0' from product_size)), ' ', size_type) " + 
			"FROM category " + 
			"NATURAL JOIN category_subcategory " + 
			"NATURAL JOIN subcategory " + 
			"NATURAL JOIN subcategory_product " + 
			"NATURAL JOIN product " + 
			"NATURAL JOIN producer " + 
			"NATURAL JOIN product_specific " + 
			"NATURAL JOIN product_size " + 
			"NATURAL JOIN product_store " + 
			"NATURAL JOIN store_specific " + 
			"NATURAL JOIN store " + 
			"WHERE category_name LIKE %?1% " + 
			"AND subcategory_name LIKE %?2% " + 
			"AND producer_name LIKE %?3% " + 
			"AND product_name LIKE %?4% " + 
			"AND store_name LIKE %?5% ", nativeQuery = true)
	List<SearchProductData> findProducts(
			String categoryName,
			String subcategoryName,
			String producerName,
			String productName,
			String storeName);
}
