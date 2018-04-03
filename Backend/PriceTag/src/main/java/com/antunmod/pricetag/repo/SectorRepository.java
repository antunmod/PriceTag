package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.Sector;

@Service
public interface SectorRepository extends JpaRepository<Sector, Long> {

	@Query(value = "SELECT sector_name FROM sector", nativeQuery = true)
	List<String> getAllSectorNames();

}
