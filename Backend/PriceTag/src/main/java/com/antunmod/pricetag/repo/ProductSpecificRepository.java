package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.antunmod.pricetag.model.database.ProductSpecific;

public interface ProductSpecificRepository extends JpaRepository<ProductSpecific, Short>{

	ProductSpecific findByProductSpecificId(Short productSpecificId);
	
	ProductSpecific findByBarcode(String barcode);
}
