package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.ProductStore;

@Service
public interface ProductStoreRepository extends JpaRepository<ProductStore, Long> {

	@Query(value = "UPDATE product_store SET "
			+ "average_price = ?2, price = ?3, price_change_date = ?4, product_updates = ?5 "
			+ "WHERE product_store_ID = ?1", nativeQuery = true)
	void saveUpdatedProduct(long productStoreId, float averagePrice, float price, String priceChangeDate,
			int productUpdates);

	ProductStore findByProductStoreId(long productStoreId);

	/*
	 * @Query(value = "", nativeQuery = true) byte[] getPhotoForProductIdAndSize(int
	 * productId, String size);
	 */
	
	@Query(value = "SELECT product_store_ID from product_store WHERE product_specific_ID = ?1 "
			+ "AND storeSpecificId = ?2", nativeQuery = true)
	short findProductStoreForProductSpecificIdAndStoreSpecificId(short productSpecificId, short storeSpecificId);

}
