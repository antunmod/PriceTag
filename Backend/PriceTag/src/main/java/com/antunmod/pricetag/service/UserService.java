package com.antunmod.pricetag.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.User;
import com.antunmod.pricetag.model.transfer.UserInformation;
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

	public UserInformation getUserInformation(Short id) {
		List<Object[]> objectInformation = userRepository.findUserInformation(id);
		if (objectInformation == null)
			return null;
		UserInformation userInformation = null;
		for(Object[] o : objectInformation) {
			userInformation = new UserInformation((String) o[0],
					(String) o[1], (Short) o[2], (String) o[3],
					((BigInteger) o[4]).intValue(), ((BigInteger) o[5]).intValue(), (String) o[6],
					(Date) o[7]);
		}
		return userInformation;
	}

}
