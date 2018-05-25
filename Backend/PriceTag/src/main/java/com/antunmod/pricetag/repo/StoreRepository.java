package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.Store;

@Service
public interface StoreRepository extends JpaRepository<Store, Short> {

	@Query(value = "SELECT store_specific.id "
			+ "FROM store JOIN store_specific ON store.id = store_specific.store_id "
			+ "WHERE address = ?1", nativeQuery = true)
	Short findStoreSpecificIdForStoreAddress(String storeAddress);

	@Query(value = "SELECT id "
			+ "FROM store WHERE name = ?1", nativeQuery = true)
	Short findStoreIdForStoreName(String storeName);
	
	@Query(value = "SELECT address "
			+ "FROM store JOIN store_specific ON store.id = store_specific.store_id "
			+ "WHERE name = ?1", nativeQuery = true)
	List<String> getStoreLocations(String storeName);

	Store findById(Short id);

	Store findByName(String name);
}
