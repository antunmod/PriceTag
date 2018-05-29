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
		//objectInformation[0];
		/*System.out.println(objectInformation.length);
		System.out.println(objectInformation[0].toString());
		//System.out.println(objectInformation[1]);

		String a = (String) objectInformation.get;
		UserInformation userInformation = new UserInformation((String) objectInformation[0],
				(String) objectInformation[1], (Short) objectInformation[2], (String) objectInformation[3],
				(Integer) objectInformation[4], (Integer) objectInformation[5], (Short) objectInformation[6],
				(String) objectInformation[7]);*/
		return userInformation;
	}

}
