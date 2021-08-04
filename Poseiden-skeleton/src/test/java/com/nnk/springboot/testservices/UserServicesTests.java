package com.nnk.springboot.testservices;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.BidListServices;
import com.nnk.springboot.services.UserServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServicesTests {
	
	@MockBean
	UserRepository userRepository; 


	private UserServices userServices;
	
	
	@Before()
	public void init() {
		
		userServices = new UserServices();
		userServices.setUserRepository(userRepository);

		
	}
	
	
	@Test
	public void test_updateAGivenUser_ShouldReturnTrueIfExist() {
		
		
		Optional<User> itemOp = Optional.ofNullable(new User());
		
		
		User testItem = new User();
		testItem.setPassword("testingPassword1@!");
		testItem.setId(5);
		
		when(userRepository.findById(5)).thenReturn(itemOp);
		
		
		Boolean actual = userServices.updateAGivenUser(testItem);
		
		assertTrue(actual == true);
		
	}
	
	@Test
	public void test_updateAGivenUser_ShouldReturnFalseIfDoNotExist() {
		

		
		
		Optional<User> itemOp = Optional.empty();
				
		when(userRepository.findById(5)).thenReturn(itemOp);

		
		User testItem = new User();
		testItem.setId(5);
				
		Boolean actual = userServices.updateAGivenUser(testItem);
		
		assertTrue(actual == false);
		
	}
	
	
	@Test
	public void test_deleteAGivenUser_ShouldReturnTrueIfExist() {
		
		
		Optional<User> itemOp = Optional.ofNullable(new User());
		

		
		when(userRepository.findById(5)).thenReturn(itemOp);
		
		
		Boolean actual = userServices.deleteAGivenUser(5);
		
		assertTrue(actual == true);
		
	}
	
	@Test
	public void test_deleteAGivenUser_ShouldReturnFalseIfDoNotExist() {
		
		
		
		Optional<User> itemOp = Optional.empty();
				
		when(userRepository.findById(5)).thenReturn(itemOp);

				
		Boolean actual = userServices.deleteAGivenUser(5);
		
		assertTrue(actual == false);
		
	}
	
	@Test
	public void test_loadUserByUsername_ShouldReturnUserDetailsIfEverythingPass() {
		
		
		User testingItem = new User();
		testingItem.setId(5);
		testingItem.setPassword("testingPassword1@!");
		testingItem.setRole("ADMIN");
		testingItem.setUsername("MLK");
	
		
		
		when(userRepository.findByUsername("MLK")).thenReturn(testingItem);
		
		
		UserDetails actual = userServices.loadUserByUsername("MLK");

		assertTrue(actual.getUsername().equals("MLK") && actual.getPassword().equals("testingPassword1@!"));
		
	}

}
