package com.nnk.springboot.testrepoanddomain;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


/**
 * By convention, at least 80% of code should be tested. 
 * Our program isn't currently using all the attributes from trades
 * (no functionality have been expressed yet by the client).
 * Therefore a lot of getters and setters aren't yet tested, because
 * there aren't currently used in the code. These untested getters and setters
 * ,plunge coverage.  Once new functionalities using these attributes
 * would be used, this test can be deleted.
 * 
 * For now, and for convention purposes, we maintain the 80% of coverage this way.
 * 
 * @author Alexandre OSSELIN
 *
 */



@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingTests {

	@Autowired
	private RatingRepository ratingRepository;

	@Test
	public void ratingTest() {
		Rating rating = new Rating();
		rating.setFitchRating("Fitch Rating");
		rating.setMoodysRating("Moodys Rating");
		rating.setOrderNumber(10);
		rating.setSandpRating("Sand PRating");
		// Save
		rating = ratingRepository.save(rating);
		Assert.assertNotNull(rating.getId());
		Assert.assertTrue(rating.getOrderNumber() == 10
				&& rating.getMoodysRating().equals("Moodys Rating") && rating.getFitchRating().equals("Fitch Rating")
				&& rating.getSandpRating().equals("Sand PRating"));

		// Update
		rating.setOrderNumber(20);
		rating = ratingRepository.save(rating);
		Assert.assertTrue(rating.getOrderNumber() == 20);

		// Find
		List<Rating> listResult = ratingRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = rating.getId();
		ratingRepository.delete(rating);
		Optional<Rating> ratingList = ratingRepository.findById(id);
		Assert.assertFalse(ratingList.isPresent());
	}
}
