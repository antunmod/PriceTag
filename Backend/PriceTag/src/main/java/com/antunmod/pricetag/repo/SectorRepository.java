package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.Sector;

@Service
public interface SectorRepository extends JpaRepository<Sector, Long>{

}
