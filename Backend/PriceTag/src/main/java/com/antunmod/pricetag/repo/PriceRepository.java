package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.Price;

@Service
public interface PriceRepository extends JpaRepository<Price, Integer> {

	Price findById(Integer id);
	
	Price findByUserIdAndPriceChangeDate(Short userId, String priceChangeDate);

	@Query(value = "SELECT price.id, " + 
			"store.name, " + 
			"store_specific.address, " + 
			"price.price, " + 
			"CONCAT(CAST((100*user.rating) AS DECIMAL(4,1)), " + 
			"	'% (', " + 
			"	CAST((SELECT COUNT(id) FROM information_feedback " + 
			"	WHERE information_provider_user_id = user.id) AS CHAR(4)), " + 
			"	')') AS user_rating " + 
			"FROM price JOIN product_store ON price.product_store_id = product_store.id " + 
			"JOIN store_specific ON store_specific.id = product_store.store_specific_id " + 
			"JOIN store ON store.id = store_specific.store_id " + 
			"JOIN user ON user.id = price.user_id " + 
			"WHERE price.id IN ( " + 
			"	SELECT MAX(price.id) " + 
			"	FROM price JOIN product_store ON price.product_store_id = product_store.id " + 
			"	WHERE product_store.product_specific_id = ?1 " + 
			"	GROUP BY product_store_id) " + 
			"GROUP BY store_specific.address " + 
			"ORDER BY price.price, store.name, store_specific.address", nativeQuery = true)
	List<Object[]> findLocationsForProductSpecificId(Short productSpecificId);
	
	@Query(value = "SELECT user_id FROM price WHERE id = ?1", nativeQuery = true)
	Short findUserIdForId(Integer priceId);
	
}
