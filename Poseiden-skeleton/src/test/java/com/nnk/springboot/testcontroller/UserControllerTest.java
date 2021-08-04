package com.nnk.springboot.testcontroller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.RatingServices;
import com.nnk.springboot.services.UserServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	
	
	@MockBean
	UserServices userServices;
	
	@MockBean
	Model model;
	
	@MockBean
	BindingResult bidingResult;
	
	private UserController userController;
	
	
	
	@Before()
	public void init() {
		userController = new UserController();
		userController.setUserServices(userServices);

	}
	
	
	@Test
	public void test_Home() {
		
		when(userServices.findAllUsers()).thenReturn(new ArrayList<User>());
		
		String actual = userController.home(model);
		
		assertTrue(actual.equals("user/list"));
	}
	
	@Test
	public void test_addUser() {
		
		String actual = userController.addUser(new User());		
		assertTrue(actual.equals("user/add"));

	}
	
	@Test
	public void test_validate_ShouldReturnBidList() {
		
		User testItem = new User();
		testItem.setPassword("testingPassword1@!");
		
		when(bidingResult.hasErrors()).thenReturn(false);
		
		String actual = userController.validate(testItem, bidingResult, model);
		
		assertTrue(actual.equals("redirect:/user/list"));
		
	}

	
	@Test
	public void test_validateWithErrorShouldReturnAddPage() {
		
		
		when(bidingResult.hasErrors()).thenReturn(true);
		
		String actual = userController.validate(new User(), bidingResult, model);
		
		assertTrue(actual.equals("user/add"));
		
	}
	
	@Test
	public void test_showUpdateForm_ShouldReturnUpdate() {
		
		when(userServices.findSpecificUser(2)).thenReturn(new User());
		
		String actual = userController.showUpdateForm(2 , model);

		assertTrue(actual.equals("user/update"));

		
	}
	
	@Test
	public void test_updateUser() {
		
		 
		User testItem = new User();
		
		when(userServices.updateAGivenUser(testItem)).thenReturn(true);

		
		String actual = userController.updateUser(1, testItem, bidingResult, model);
		
		
		assertTrue(actual.equals("redirect:/user/list"));

	}
	
	
	@Test
	public void test_deleteUser() {
		
		String actual = userController.deleteUser(1, model);
		
		assertTrue(actual.equals("redirect:/user/list"));

		
	}

}
