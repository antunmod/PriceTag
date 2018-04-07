package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.Store;

@Service
public interface StoreRepository extends JpaRepository<Store, Byte> {

	@Query(value = "SELECT store_ID FROM store natural join store_location "
			+ "WHERE store_address = ?1", nativeQuery = true)
	Integer findStoreIdForStoreAddress(String storeAddress);

	@Query(value = "SELECT store_address FROM store_location natural join store "
			+ "WHERE store_name = ?1", nativeQuery = true)
	List<String> getStoreLocations(String storeName);
	
	Store findByStoreId(Byte storeId);
}
