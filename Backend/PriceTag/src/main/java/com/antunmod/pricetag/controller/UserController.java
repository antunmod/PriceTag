package com.antunmod.pricetag.controller;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antunmod.pricetag.model.User;
import com.antunmod.pricetag.repo.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@ResponseBody
	@GetMapping("")
	public ResponseEntity<User> loginUser(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		User user = userRepository.findByUserNameAndPassword(username, password);
		if (user == null) {
			return new ResponseEntity<User>(new User(), HttpStatus.OK);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping("")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		User savedUser;
		try {
			savedUser = userRepository.save(user);
		} catch (Exception e) {
			return new ResponseEntity<User>(new User(), HttpStatus.OK);
		}
		
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping("/awardPoints")
	public ResponseEntity<Boolean> awardPointsToUserForUserId(@RequestParam("userId") long userId,
															@RequestParam("points") int points) {
		User user, savedUser;
		user = userRepository.findByUserId(userId);
		
		user.setPoints(user.getPoints()+points);
		savedUser = userRepository.save(user);
		if(savedUser.getPoints()<=user.getPoints()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
}
