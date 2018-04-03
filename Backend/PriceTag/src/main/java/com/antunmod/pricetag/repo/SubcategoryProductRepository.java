package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.SubcategoryProduct;

@Service
public interface SubcategoryProductRepository extends JpaRepository<SubcategoryProduct, Long> {

	@Query(value = "SELECT subcategory_ID FROM subcategory where subcategory_name = ?1 ", nativeQuery = true)
	Integer getSubcategoryIdForSubcategoryName(String subcategoryName);

}
