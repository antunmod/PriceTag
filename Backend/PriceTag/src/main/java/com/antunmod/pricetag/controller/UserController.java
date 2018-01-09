package com.antunmod.pricetag.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
//	@ResponseBody
//	@GetMapping("")
//	public ResponseEntity showUserList () {
//		return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
//	}
	
	@ResponseBody
	@GetMapping("")
	public ResponseEntity loginUser (@RequestParam("username") String username) {
		
		User user = userRepository.findByUserName(username);
		if (user==null) {
			String error = "Nepostojeci korisnik.";
            return new ResponseEntity<String>(error, HttpStatus.OK);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping("/save")
	public String saveUser() {
		userRepository.save(new User("newUser", "newpass", "example@pricetag.com", new Date(Calendar.getInstance().getTime().getTime()), 0, 1));
		return "saved";
	}
	
	
	
}
