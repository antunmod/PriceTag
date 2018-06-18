package com.antunmod.pricetag.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.User;

@Service
public interface UserRepository extends JpaRepository<User, Short> {

	// FIND_USER
	@Query(value = "SELECT * FROM user WHERE name LIKE ?1", nativeQuery = true)
	User findByUserName(String userName);

	@Query(value = "SELECT * FROM user WHERE name LIKE ?1 and password LIKE ?2", nativeQuery = true)
	User findByUserNameAndPassword(String username, String password);

	User findById(Short id);

	
	@Query(value = "SELECT user.name, user.email, user.points, " + 
			"CONCAT(TRIM(TRAILING '.' FROM TRIM(TRAILING '0' from 100*rating)), '%') AS information_validity, " + 
			"(select count(*) FROM information_feedback WHERE information_provider_user_id = ?1) AS feedbacks_received, " + 
			"(select count(*) FROM information_feedback WHERE feedback_provider_user_id = ?1) AS feedbacks_given, " + 
			"user_type.description, user.signup_date " + 
			"FROM user JOIN user_type ON user.user_type_id = user_type.id " +
			"WHERE user.id = ?1 LIMIT 1", nativeQuery = true)
	List<Object[]> findUserInformation(Short id);
	
	@Query(value = "SELECT password FROM user WHERE name = ?1 AND email = ?2", nativeQuery = true)
	String findPasswordForUsernameAndEmail(String username, String email);
}
