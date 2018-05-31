package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.antunmod.pricetag.model.database.ProductSpecific;
import com.antunmod.pricetag.model.transfer.ProductInformation;

public interface ProductSpecificRepository extends JpaRepository<ProductSpecific, Short>{

	@Query(value = "SELECT id FROM product_specific WHERE barcode = ?1", nativeQuery = true)
	Short findProductSpecificIdForBarcode(String barcode);
	
	ProductSpecific findById(Short id);
	
	ProductSpecific findByBarcode(String barcode);
	
	@Query(value = "SELECT DISTINCT product_specific.id, " + 
			"image_URI, " + 
			"producer.name AS producer_name, " + 
			"product.name AS product_name, " + 
			"product_specific.description, " + 
			"CONCAT(TRIM(TRAILING '.' FROM TRIM(TRAILING '0' from size)), ' ', type) AS size " + 
			"FROM category " + 
			"JOIN category_subcategory ON category.id = category_subcategory.category_id " + 
			"JOIN subcategory ON subcategory.id = category_subcategory.subcategory_id " + 
			"JOIN subcategory_product ON subcategory.id = subcategory_product.subcategory_id " + 
			"JOIN product ON product.id = subcategory_product.product_id " + 
			"JOIN producer ON producer.id = product.producer_id " + 
			"JOIN product_specific ON product_specific.product_id = product.id " + 
			"JOIN product_size ON product_size.id = product_specific.size_id " + 
			"JOIN product_store ON product_store.product_specific_id= product_specific.id " + 
			"JOIN store_specific ON store_specific.id = product_store.store_specific_id " + 
			"JOIN store ON store.id = store_specific.store_id " + 
			"WHERE category.name LIKE %?1% " + 
			"AND subcategory.name LIKE %?2% " + 
			"AND producer.name LIKE %?3% " + 
			"AND product.name LIKE %?4% " + 
			"AND store.name LIKE %?5% " +
			"ORDER BY producer_name, product_name, product_specific.description, size", nativeQuery = true)
	List<Object[]> findProducts(
			String categoryName,
			String subcategoryName,
			String producerName,
			String productName,
			String storeName);

	@Query(value = "SELECT product_specific.id, " + 
			"image_URI, " +
			"CONCAT(producer.name, " +
			"' ', " +
			"product.name, " +
			"' ',  " +
			"TRIM(TRAILING '.' FROM TRIM(TRAILING '0' from size)), ' ', type) " +
			"FROM product " +
			"JOIN producer ON product.producer_id = producer.id " +
			"JOIN product_specific on product.id = product_specific.product_id " +
			"JOIN product_size ON product_specific.size_id = product_size.id " + 
			"WHERE barcode = ?1", nativeQuery = true)
	List<Object[]> getProductInformationForBarcode(String barcode);
	
	@Query(value = "SELECT DISTINCT product_specific.id, " + 
			"image_URI, " + 
			"producer.name AS producer_name, " + 
			"product.name AS product_name, " + 
			"product_specific.description, " + 
			"CONCAT(TRIM(TRAILING '.' FROM TRIM(TRAILING '0' from size)), ' ', type) AS size " + 
			"FROM category " + 
			"JOIN category_subcategory ON category.id = category_subcategory.category_id " + 
			"JOIN subcategory ON subcategory.id = category_subcategory.subcategory_id " + 
			"JOIN subcategory_product ON subcategory.id = subcategory_product.subcategory_id " + 
			"JOIN product ON product.id = subcategory_product.product_id " + 
			"JOIN producer ON producer.id = product.producer_id " + 
			"JOIN product_specific ON product_specific.product_id = product.id " + 
			"JOIN product_size ON product_size.id = product_specific.size_id " + 
			"JOIN product_store ON product_store.product_specific_id= product_specific.id " + 
			"JOIN store_specific ON store_specific.id = product_store.store_specific_id " + 
			"JOIN store ON store.id = store_specific.store_id " +
			"JOIN price ON price.product_store_id = product_store.id " + 
			"ORDER BY price.price_change_date " +
			"LIMIT 20", nativeQuery = true)
	List<Object[]> findRecentProducts();
}
