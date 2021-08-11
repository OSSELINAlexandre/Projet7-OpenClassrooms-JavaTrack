package com.nnk.springboot.testservices;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingServicesTests {

	@MockBean
	RatingRepository ratingRepository;

	private ArgumentCaptor<Rating> ratingCaptor = ArgumentCaptor.forClass(Rating.class);

	private RatingServices ratingServices;

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

		verify(ratingRepository, times(1)).save(ratingCaptor.capture());
		Rating actualItem = ratingCaptor.getValue();

		assertTrue(actual == true && actualItem.getId() == 5);

	}

	@Test
	public void test_updateAGivenRating_ShouldReturnFalseIfDoNotExist() {

		Optional<Rating> itemOp = Optional.empty();

		when(ratingRepository.findById(5)).thenReturn(itemOp);

		Rating testItem = new Rating();
		testItem.setId(5);

		Boolean actual = ratingServices.updateAGivenRating(testItem);

		verify(ratingRepository, times(0)).save(ratingCaptor.capture());

		assertTrue(actual == false);

	}

	@Test
	public void test_deleteAGivenCurvePoint_ShouldReturnTrueIfExist() {

		Optional<Rating> itemOp = Optional.ofNullable(new Rating());

		when(ratingRepository.findById(5)).thenReturn(itemOp);

		Boolean actual = ratingServices.deleteAGivenRating(5);

		verify(ratingRepository, times(1)).deleteById(5);

		assertTrue(actual == true);

	}

	@Test
	public void test_deleteAGivenCurvePoint_ShouldReturnFalseIfDoNotExist() {

		Optional<Rating> itemOp = Optional.empty();

		when(ratingRepository.findById(5)).thenReturn(itemOp);

		Boolean actual = ratingServices.deleteAGivenRating(5);

		verify(ratingRepository, times(0)).deleteById(5);

		assertTrue(actual == false);

	}

}
