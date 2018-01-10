package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.CategorySubcategory;

@Service
public interface CategorySubcategoryRepository extends JpaRepository<CategorySubcategory, Long> {

}
