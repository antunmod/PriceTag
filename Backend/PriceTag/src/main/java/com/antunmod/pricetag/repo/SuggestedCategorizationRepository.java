package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.SuggestedCategorization;

@Service
public interface SuggestedCategorizationRepository extends JpaRepository<SuggestedCategorization, String>{

}
