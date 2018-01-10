package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.SubcategoryProduct;

@Service
public interface SubcategoryProductRepository extends JpaRepository<SubcategoryProduct, Long> {

}
