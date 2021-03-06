package com.antunmod.pricetag.controller;

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

import com.antunmod.pricetag.model.database.User;
import com.antunmod.pricetag.model.transfer.UserInformation;
import com.antunmod.pricetag.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@ResponseBody
	@GetMapping("")
	public ResponseEntity<User> loginUser(@RequestParam("name") String name,
			@RequestParam("password") String password) {
		User user = userService.getUser(name, password);
		if (user == null) {
			return new ResponseEntity<User>(new User(), HttpStatus.OK);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping("")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		User savedUser = userService.saveUser(user);

		if (user == null)
			return new ResponseEntity<User>(new User(), HttpStatus.OK);

		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/information")
	public ResponseEntity<UserInformation> getUserInformation(@RequestParam("id") Short id) {
		UserInformation userInformation= userService.getUserInformation(id);
		return new ResponseEntity<UserInformation>(userInformation, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/requestPassword")
	public ResponseEntity<Boolean> sendPasswordToEmail(@RequestParam("username") String username,
			@RequestParam("email") String email) {
		Boolean passwordSent = userService.sendPasswordToEmail(username, email);
		return new ResponseEntity<Boolean>(passwordSent, HttpStatus.OK);
	}

}
