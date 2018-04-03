package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.User;

@Service
public interface UserRepository extends JpaRepository<User, Long> {

	// FIND_USER
	@Query(value = "SELECT * FROM user u WHERE u.user_name LIKE ?1", nativeQuery = true)
	User findByUserName(String username);

	@Query(value = "SELECT * FROM user u WHERE u.user_name LIKE ?1 and user_password LIKE ?2", nativeQuery = true)
	User findByUserNameAndPassword(String username, String password);

	User findByUserId(long userId);

}
