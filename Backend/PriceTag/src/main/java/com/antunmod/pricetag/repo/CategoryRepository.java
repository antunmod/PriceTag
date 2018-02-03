package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.Category;

@Service
public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(value = "SELECT category_name FROM sector NATURAL JOIN sector_category NATURAL JOIN category "
			+ "WHERE sector_name = ?1", nativeQuery = true)
	List<String> findAllForSectorName(String sectorName);

}
