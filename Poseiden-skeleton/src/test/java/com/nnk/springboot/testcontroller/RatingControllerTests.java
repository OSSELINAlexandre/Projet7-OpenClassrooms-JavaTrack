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
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingControllerTests {
	
	
	@MockBean
	RatingServices ratingServices;
	
	@MockBean
	Model model;
	
	@MockBean
	BindingResult bidingResult;
	
	private RatingController ratingController;
	
	
	
	@Before()
	public void init() {
		ratingController = new RatingController();
		ratingController.setRatingServices(ratingServices);

	}
	
	
	@Test
	public void test_Home() {
		
		when(ratingServices.findAllRatings()).thenReturn(new ArrayList<Rating>());
		
		String actual = ratingController.home(model);
		
		assertTrue(actual.equals("rating/list"));
	}
	
	@Test
	public void test_addRatingForm() {
		
		String actual = ratingController.addRatingForm(new Rating());		
		assertTrue(actual.equals("rating/add"));

	}
	
	@Test
	public void test_validate_ShouldReturnBidList() {
		
		
		when(bidingResult.hasErrors()).thenReturn(false);
		
		String actual = ratingController.validate(new Rating(), bidingResult, model);
		
		assertTrue(actual.equals("redirect:/rating/list"));
		
	}

	
	@Test
	public void test_validateWithErrorShouldReturnAddPage() {
		
		
		when(bidingResult.hasErrors()).thenReturn(true);
		
		String actual = ratingController.validate(new Rating(), bidingResult, model);
		
		assertTrue(actual.equals("rating/add"));
		
	}
	
	@Test
	public void test_showUpdateForm_ShouldReturnUpdate() {
		
		when(ratingServices.findSpecificRating(2)).thenReturn(new Rating());
		
		String actual = ratingController.showUpdateForm(2 , model);

		assertTrue(actual.equals("rating/update"));

		
	}
	
	@Test
	public void test_updateRating() {
		
		 
		Rating testItem = new Rating();
		
		when(ratingServices.updateAGivenRating(testItem)).thenReturn(true);

		
		String actual = ratingController.updateRating(1, testItem, bidingResult, model);
		
		
		assertTrue(actual.equals("redirect:/rating/list"));

	}
	
	
	@Test
	public void test_deleteRating() {
		
		String actual = ratingController.deleteRating(1, model);
		
		assertTrue(actual.equals("redirect:/rating/list"));

		
	}

	

}
