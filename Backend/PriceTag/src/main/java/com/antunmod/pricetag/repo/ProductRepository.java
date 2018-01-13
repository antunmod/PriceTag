package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.Product;

@Service
public interface ProductRepository extends JpaRepository<Product, Long>{

	
	
}
