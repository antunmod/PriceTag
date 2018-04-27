package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.Price;

@Service
public interface PriceRepository extends JpaRepository<Price, Integer> {

	Price findById(Integer id);
	
	Price findByUserIdAndPriceChangeDate(Short userId, String priceChangeDate);
}
