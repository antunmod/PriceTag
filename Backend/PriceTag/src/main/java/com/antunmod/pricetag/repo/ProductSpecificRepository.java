package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.antunmod.pricetag.model.ProductSpecific;

public interface ProductSpecificRepository extends JpaRepository<ProductSpecific, Long>{

}
