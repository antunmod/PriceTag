package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.InformationFeedback;

@Service
public interface InformationFeedbackRepository extends JpaRepository<InformationFeedback, Integer> {

	InformationFeedback findById(Integer id);
	
	@Query(value = "SELECT * FROM information_feedback " +
			"WHERE information_provider_user_id = ?1 " +
			"AND price_id = ?2", nativeQuery = true)
	InformationFeedback findByUserAndPriceId(Short userId, Integer priceId);

}
