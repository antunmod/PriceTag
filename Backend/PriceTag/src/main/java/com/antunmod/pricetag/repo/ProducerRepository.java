package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.Producer;

@Service
public interface ProducerRepository extends JpaRepository<Producer, Short> {

	Producer findById(Short id);
	
	Producer findByName(String name);
	
	@Query(value = "SELECT id FROM producer WHERE name = ?1", nativeQuery = true)
	Short getProducerId(String producerName);
}
