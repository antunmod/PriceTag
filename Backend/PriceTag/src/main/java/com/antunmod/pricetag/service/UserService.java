package com.antunmod.pricetag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.User;
import com.antunmod.pricetag.repo.UserRepository;

/*
 *	 This is the Service class which handles user data.
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User getUser(String username, String password) {
		User user = userRepository.findByUserNameAndPassword(username, password);
		if (user == null) {
			return new User();
		}
		return user;
	}

	public User saveUser(User user) {
		User savedUser = userRepository.save(user);
		if (user == null) {
			return new User();
		}
		return savedUser;
	}
	
	
	
}
