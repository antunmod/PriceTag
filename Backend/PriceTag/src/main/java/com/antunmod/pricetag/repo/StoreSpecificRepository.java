package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.StoreSpecific;

@Service
public interface StoreSpecificRepository extends JpaRepository<StoreSpecific, Short> {

	StoreSpecific findByStoreSpecificId(Short storeSpecificId);
	
	StoreSpecific findByStoreIdAndStoreAddress(Byte storeId, String storeAddress);
}
