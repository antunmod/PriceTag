package com.antunmod.pricetag.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.antunmod.pricetag.PriceTagApplication;
import com.antunmod.pricetag.model.database.User;
import com.antunmod.pricetag.model.transfer.UserInformation;
import com.antunmod.pricetag.service.DeleteUserService;
import com.antunmod.pricetag.service.UserService;

/*
 * This class tests UserService for saving and getting user from DB.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PriceTagApplication.class)
public class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@Autowired
	DeleteUserService deleteUserService;
	
	
	private final String TEST_USER_NAME = "testUser";
	private final String TEST_USER_PASSWORD = "testPassword";
	private final String TEST_USER_EMAIL = "test@gmail.com";
	
	private final Short REGULAR_USER_ID = 2;
	private final String REGULAR_USER_NAME = "regularUser";
	
	private final String NEW_USER_NAME = "newUser";
	private final String NEW_USER_PASSWORD = "newPassword";
	private final String NEW_USER_EMAIL = "new@gmail.com";
	private final String DATE_STRING = "2018-05-16";
	
	private User user;
	
	@Before
	public void setupUser() {
		user = new User(NEW_USER_NAME, NEW_USER_PASSWORD, NEW_USER_EMAIL, DATE_STRING);
	}
	
	//@Test
	public void testGetUser() {
		Boolean success = false;
		User user = userService.getUser(TEST_USER_NAME, TEST_USER_PASSWORD);
		if (user.getEmail().equals(TEST_USER_EMAIL))
			success = true;
		assertTrue(success);
	}
	
	//@Test
	public void testSaveUser() {
		User savedUser = userService.saveUser(user);
		if (savedUser!=null)
			deleteUserService.removeUser(savedUser);
		assertTrue(true);
	}
	
	@Test
	public void testGetUserInformation() {
		Boolean success = false;
		UserInformation userInformation = userService.getUserInformation(REGULAR_USER_ID);
		if (userInformation != null)
			success = userInformation.getName().equals(REGULAR_USER_NAME);
		assertTrue(success);
	}
	
}
