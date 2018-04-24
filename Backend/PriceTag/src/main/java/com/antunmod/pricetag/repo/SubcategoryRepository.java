package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.Sector;
import com.antunmod.pricetag.model.database.Subcategory;

@Service
public interface SubcategoryRepository extends JpaRepository<Subcategory, Short> {

	Subcategory findBySubcategoryId(Short subcategoryId);
	
	@Query(value = "SELECT subcategory_name FROM category NATURAL JOIN category_subcategory NATURAL JOIN subcategory "
			+ "WHERE category_name = ?1", nativeQuery = true)
	List<String> findAllForCategoryName(String categoryName);

	@Query(value = "SELECT subcategory_ID from subcategory NATURAL JOIN category_subcategory NATURAL JOIN category WHERE "
			+ "category_name = ?1 and subcategory_name = ?2", nativeQuery = true)
	Short findSubcategoryIdForCategoryAndSubcategoryName(String categoryName, String subcategoryName);

}
