package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.Category;

@Service
public interface CategoryRepository extends JpaRepository<Category, Byte> {

	Category findById(Byte id);
	
	@Query(value = "SELECT category.name "
			+ "FROM sector JOIN sector_category ON sector.id = sector_category.sector_id "
			+ "JOIN category ON category.id = sector_category.category_id "
			+ "WHERE sector.name = ?1", nativeQuery = true)
	List<String> findAllForSectorName(String sectorName);

}
