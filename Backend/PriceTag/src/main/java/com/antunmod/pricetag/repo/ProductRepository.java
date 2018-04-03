package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.Product;

@Service
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query(value = "SELECT distinct producer FROM product NATURAL JOIN subcategory_product NATURAL JOIN subcategory WHERE " + 
			" subcategory_name = ?1", nativeQuery=true)
	List<String> findAllForSubcategoryName(String subcategoryName);
	
	@Query(value = "SELECT product_name FROM product NATURAL JOIN subcategory_product NATURAL JOIN " +
			"subcategory WHERE subcategory_name = ?1 and producer = ?2", nativeQuery=true)
	List<String> getProductNamesForSubcategoryNameAndProducer(String subcategoryName, String producer);
	
	@Query(value = "SELECT product_ID FROM product WHERE producer = ?1 AND product_name = ?2", nativeQuery=true)
	Integer getProductIdForProducerAndProductName(String producer, String productName);
	
	@Query(value = "select concat(product_size, ' ', size_type) as size from product_specific natural join product_size where" + 
			"	product_ID = ?1", nativeQuery=true)
	List<String> getSizeValuesForProductId(int productId);
	
}
