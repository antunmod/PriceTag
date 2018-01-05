package com.antunmod.pricetag.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antunmod.pricetag.model.User;
import com.antunmod.pricetag.repo.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("")
	public List<User> showUserList () {
		return userRepository.findAll();
	}
	
	@RequestMapping("/save")
	public String saveUser() {
		userRepository.save(new User("newUser", "newpass", "example@pricetag.com", new Date(Calendar.getInstance().getTime().getTime()), 0, 1));
		return "saved";
	}
	
	
	
}
