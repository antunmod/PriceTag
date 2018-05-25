package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.UserType;

@Service
public interface UserTypeRepository extends JpaRepository<UserType, Byte> {

	public UserType findById (Byte id);
}
