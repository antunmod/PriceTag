package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.SectorCategory;

@Service
public interface SectorCategoryRepository extends JpaRepository<SectorCategory, Long> {

}
