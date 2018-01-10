package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.Category;

@Service
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
