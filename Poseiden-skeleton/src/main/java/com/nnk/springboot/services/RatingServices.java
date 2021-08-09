package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
public class RatingServices {
	
	
	
	@Autowired
	private RatingRepository ratingRepository;


	
	public List<Rating> findAllRatings() {

		return ratingRepository.findAll();
	}

	public Rating saveANewRating(@Valid Rating rating) {

		return ratingRepository.save(rating);
	}

	public Rating findSpecificRating(Integer id) {
		return ratingRepository.findById(id).get();
	}

	public Boolean updateAGivenRating(@Valid Rating rating) {
		
		Optional<Rating> foundItem = ratingRepository.findById(rating.getId());
		
		if(foundItem.isEmpty()) {
			
			return false;
			
		}else {
			
			ratingRepository.save(rating);
			return true;
			
		}
	}

	public Boolean deleteAGivenRating(Integer id) {

		Optional<Rating> foundItem = ratingRepository.findById(id);
		
		if(foundItem.isEmpty()) {
			
			return false;
			
		}else {
			
			ratingRepository.deleteById(id);
			return true;
			
		}
	}

	public void setRatingRepository(RatingRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}
	
	
	

}
