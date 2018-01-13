package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.Subcategory;

@Service
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

	
	@Query(value = "SELECT subcategory_name FROM category NATURAL JOIN category_subcategory NATURAL JOIN subcategory "
			+ "WHERE category_name = ?1", nativeQuery=true)
	List<String> findAllForCategoryName(String categoryName);
	
}
