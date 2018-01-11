package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.Store;
import com.antunmod.pricetag.model.UpdateProduct;

@Service
public interface StoreRepository extends JpaRepository<Store, Long> {

	//FIND LOCATIONS FOR STORE NAME
		@Query(value = "SELECT store_address " + 
				"FROM store WHERE " + 
				"store_name = ?1", nativeQuery=true)
		List<String> getStoreLocations(String storeName);
	
}
