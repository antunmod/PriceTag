package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.InformationFeedback;

@Service
public interface InformationFeedbackRepository extends JpaRepository<InformationFeedback, Integer> {

	InformationFeedback findById(Integer id);

}
