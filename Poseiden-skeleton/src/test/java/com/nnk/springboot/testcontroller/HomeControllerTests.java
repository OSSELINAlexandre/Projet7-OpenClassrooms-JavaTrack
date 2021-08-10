package com.nnk.springboot.testcontroller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import com.nnk.springboot.controllers.HomeController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeControllerTests {

	@MockBean
	Model model;
	
	private HomeController homeController;
	
	@Before()
	public void init() {
		
		homeController = new HomeController();
		
	}
	
	@Test
	public void test_home() {
		
		String actual = homeController.home(model);
		assertTrue(actual.equals("home"));


	}
	
	@Test
	public void test_adminHome() {
		
		String actual = homeController.adminHome(model);
		assertTrue(actual.equals("redirect:/bidList/list"));


	}
	@Test
	public void test_returnTheLoginPage() {
		
		String actual = homeController.returnTheLoginPage(model);
		assertTrue(actual.equals("login"));


	}
}
