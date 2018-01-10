package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.Subcategory;

@Service
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

}
