package com.antunmod.pricetag.controller;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.antunmod.pricetag.model.User;
import com.antunmod.pricetag.repo.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showUserList (Model model) {
		String result = "<html>";
        
        for(User user : userRepository.findAll()){
            result += "<div>" + user.toString() + "</div>";
        }
          
        return result + "</html>";
	}
	
	@RequestMapping("/save")
	public String saveUser() {
		userRepository.save(new User("newUser", "newpass", "example@pricetag.com", new Date(Calendar.getInstance().getTime().getTime()), 0, 1));
		return "saved";
	}
	
	
	
}
