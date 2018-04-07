package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.ProductStore;

@Service
public interface ProductStoreRepository extends JpaRepository<ProductStore, Short> {

	@Query(value = "SELECT product_store_ID from product_store WHERE product_specific_ID = ?1 "
			+ "AND storeSpecificId = ?2", nativeQuery = true)
	short findProductStoreForProductSpecificIdAndStoreSpecificId(short productSpecificId, short storeSpecificId);

	ProductStore findByProductStoreId(Short productStoreId);
}
