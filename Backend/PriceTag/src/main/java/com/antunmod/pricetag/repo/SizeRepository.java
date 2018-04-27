package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.antunmod.pricetag.model.database.Size;

public interface SizeRepository extends JpaRepository<Size, Byte> {

	@Query(value = "SELECT type FROM product_size", nativeQuery = true)
	List<String> findSizeTypes();

	Size findById(Byte id);
	
}
