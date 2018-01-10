package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.antunmod.pricetag.model.Size;

public interface SizeRepository extends JpaRepository<Size, Long>{
	
}
