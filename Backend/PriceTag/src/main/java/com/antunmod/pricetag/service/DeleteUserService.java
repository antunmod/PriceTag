package com.antunmod.pricetag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.User;
import com.antunmod.pricetag.repo.UserRepository;

/*
 *	 This is the Service class which is used only in testing by UserServiceTest.
 */
@Service
public class DeleteUserService {

	@Autowired
	private UserRepository userRepository;
	
	public Boolean removeUser(User user) {
		userRepository.delete(user);
		return true;
	}
}
