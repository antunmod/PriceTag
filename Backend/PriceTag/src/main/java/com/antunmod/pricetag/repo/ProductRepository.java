package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.Product;

@Service
public interface ProductRepository extends JpaRepository<Product, Short> {

	@Query(value = "SELECT producer_name FROM producer NATURAL JOIN product NATURAL JOIN subcategory_product NATURAL JOIN subcategory WHERE "
			+ " subcategory_name = ?1", nativeQuery = true)
	List<String> findAllForSubcategoryName(String subcategoryName);

	@Query(value = "SELECT product_name FROM producer NATURAL JOIN product NATURAL JOIN subcategory_product NATURAL JOIN "
			+ "subcategory WHERE subcategory_name = ?1 and producer_name = ?2", nativeQuery = true)
	List<String> getProductNamesForSubcategoryNameAndProducer(String subcategoryName, String producerName);

	@Query(value = "SELECT product_ID FROM product NATURAL JOIN producer WHERE producer_name = ?1 AND product_name = ?2", nativeQuery = true)
	Short getProductIdForProducerAndProductName(String producer, String productName);

	@Query(value = "SELECT CONCAT(TRIM(TRAILING '.' FROM TRIM(TRAILING '0' from product_size)), ' ', size_type) " + 
			"FROM product_specific NATURAL JOIN product_size WHERE product_ID = ?1", nativeQuery = true)
	List<String> getSizeValuesForProductId(Short productId);

	Product findByProductId(Short productId);

	Product findByProductNameAndProducerId(String productName, Short producerId);
}
