package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.Sector;
import com.antunmod.pricetag.model.database.Subcategory;

@Service
public interface SubcategoryRepository extends JpaRepository<Subcategory, Short> {

	Subcategory findById(Short id);
	
	@Query(value = "SELECT subcategory.name "
			+ "FROM category JOIN category_subcategory ON category.id = category_subcategory.category_id "
			+ "JOIN subcategory ON subcategory.id = category_subcategory.subcategory_id "
			+ "WHERE category.name = ?1", nativeQuery = true)
	List<String> findAllForCategoryName(String categoryName);

	@Query(value = "SELECT subcategory.id "
			+ "FROM category JOIN category_subcategory ON category.id = category_subcategory.category_id "
			+ "JOIN subcategory ON subcategory.id = category_subcategory.subcategory_id "
			+ "WHERE category.name = ?1 and subcategory.name = ?2", nativeQuery = true)
	Short findSubcategoryIdForCategoryAndSubcategoryName(String categoryName, String subcategoryName);

}
