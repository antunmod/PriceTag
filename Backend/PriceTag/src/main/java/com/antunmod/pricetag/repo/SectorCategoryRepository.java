package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.SectorCategory;

@Service
public interface SectorCategoryRepository extends JpaRepository<SectorCategory, Byte> {

	SectorCategory findByCategoryId(Byte categoryId);
	
}
