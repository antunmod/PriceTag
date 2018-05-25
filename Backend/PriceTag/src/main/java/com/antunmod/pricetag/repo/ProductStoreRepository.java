package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.ProductStore;

@Service
public interface ProductStoreRepository extends JpaRepository<ProductStore, Short> {

	@Query(value = "SELECT id from product_store WHERE product_specific_id = ?1 "
			+ "AND store_specific_id = ?2", nativeQuery = true)
	Short findProductStoreForProductSpecificIdAndStoreSpecificId(Short productSpecificId, Short storeSpecificId);

	ProductStore findById(Short id);
	
	ProductStore findByProductSpecificIdAndStoreSpecificId(Short productSpecificId, Short storeSpecificId);
}
