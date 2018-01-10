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

	@RequestMapping("/save")
	public String saveUser() {
		userRepository.save(new User("newUser", "newpass", "example@pricetag.com",
				new Date(Calendar.getInstance().getTime().getTime()), 0, 1));
		return "saved";
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
	
}
