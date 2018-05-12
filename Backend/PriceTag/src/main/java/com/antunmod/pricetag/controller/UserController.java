package com.antunmod.pricetag.controller;

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
import com.antunmod.pricetag.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	@ResponseBody
	@GetMapping("")
	public ResponseEntity<User> loginUser(@RequestParam("name") String name,
			@RequestParam("password") String password) {
		User user = userService.getUser(userName, password);
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

	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/awardPoints") public ResponseEntity<Boolean>
	 * awardPointsToUserForUserId(@RequestParam("userId") long userId,
	 * 
	 * @RequestParam("points") Short points) { User user, savedUser; user =
	 * userRepository.findByUserId(userId);
	 * 
	 * user.setPoints(user.getPoints() + points); savedUser =
	 * userRepository.save(user); if (savedUser.getPoints() <= user.getPoints()) {
	 * return new ResponseEntity<Boolean>(false, HttpStatus.OK); }
	 * 
	 * return new ResponseEntity<Boolean>(true, HttpStatus.OK); }
	 */

}
