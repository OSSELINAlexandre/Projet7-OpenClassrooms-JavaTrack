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
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.CurvePointServices;
import com.nnk.springboot.services.RatingServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingServicesTests {

	
	@MockBean
	RatingRepository ratingRepository;


	private RatingServices ratingServices;
	
	private Rating testingItem;
	
	@Before()
	public void init() {
		
		ratingServices = new RatingServices();
		ratingServices.setRatingRepository(ratingRepository);

		
	}
	
	
	@Test
	public void test_updateAGivenRatingt_ShouldReturnTrueIfExist() {
		
		
		Optional<Rating> itemOp = Optional.ofNullable(new Rating());
		
		
		Rating testItem = new Rating();
		testItem.setId(5);
		
		when(ratingRepository.findById(5)).thenReturn(itemOp);
		
		
		Boolean actual = ratingServices.updateAGivenRating(testItem);
		
		assertTrue(actual == true);
		
	}
	
	@Test
	public void test_updateAGivenRating_ShouldReturnFalseIfDoNotExist() {
		

		
		
		Optional<Rating> itemOp = Optional.empty();
				
		when(ratingRepository.findById(5)).thenReturn(itemOp);

		
		Rating testItem = new Rating();
		testItem.setId(5);
				
		Boolean actual = ratingServices.updateAGivenRating(testItem);
		
		assertTrue(actual == false);
		
	}
	
	
	@Test
	public void test_deleteAGivenCurvePoint_ShouldReturnTrueIfExist() {
		
		
		Optional<Rating> itemOp = Optional.ofNullable(new Rating());
		

		when(ratingRepository.findById(5)).thenReturn(itemOp);
		
		
		Boolean actual = ratingServices.deleteAGivenRating(5);
		
		assertTrue(actual == true);
		
	}
	
	@Test
	public void test_deleteAGivenCurvePoint_ShouldReturnFalseIfDoNotExist() {
		
		

		
		Optional<Rating> itemOp = Optional.empty();
				
		when(ratingRepository.findById(5)).thenReturn(itemOp);


		Boolean actual = ratingServices.deleteAGivenRating(5);
		
		assertTrue(actual == false);
		
	}



}
