package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.Product;

@Service
public interface ProductRepository extends JpaRepository<Product, Short> {

	@Query(value = "SELECT producer.name "
			+ "FROM producer JOIN product ON producer.id = product.producer_id "
			+ "JOIN subcategory_product ON subcategory_product.product_id = product.id "
			+ "JOIN subcategory ON subcategory.id = subcategory_product.subcategory_id "
			+ "WHERE subcategory.name = ?1", nativeQuery = true)
	List<String> findAllForSubcategoryName(String subcategoryName);

	@Query(value = "SELECT product.name "
			+ "FROM producer JOIN product ON producer.id = product.producer_id "
			+ "JOIN subcategory_product ON subcategory_product.product_id = product.id "
			+ "JOIN subcategory ON subcategory.id = subcategory_product.subcategory_id "
			+ "WHERE subcategory.name = ?1 and producer.name = ?2", nativeQuery = true)
	List<String> getProductNamesForSubcategoryAndProducerName(String subcategoryName, String producerName);

	@Query(value = "SELECT product.id "
			+ "FROM product JOIN producer ON product.producer_id = producer.id "
			+ "WHERE producer.name = ?1 AND product.name = ?2", nativeQuery = true)
	Short getProductIdForProducerAndProductName(String producer, String productName);

	@Query(value = "SELECT CONCAT(TRIM(TRAILING '.' FROM TRIM(TRAILING '0' from size)), ' ', type) "
			+ "FROM product_specific JOIN product_size ON product_specific.size_id = product_size.id "
			+ "WHERE product_id = ?1", nativeQuery = true)
	List<String> getSizeValuesForProductId(Short productId);

	Product findById(Short id);

	Product findByNameAndProducerId(String name, Short producerId);
}
