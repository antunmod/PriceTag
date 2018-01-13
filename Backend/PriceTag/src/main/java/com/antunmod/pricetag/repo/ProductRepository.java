package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.Product;

@Service
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query(value = "select producer from product natural join product_store natural join subcategory_product natural join subcategory where " + 
			" subcategory_name = ?1", nativeQuery=true)
	List<String> findAllForSubcategoryName(String subcategoryName);
	
}
