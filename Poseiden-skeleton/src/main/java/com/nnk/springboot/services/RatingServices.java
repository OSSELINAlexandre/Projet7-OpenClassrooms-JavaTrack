package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

/**
 * <b>RatingServices est le service permettant d'insérer la logique business
 * dans le domaine métier Rating.</b>
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */
@Service
public class RatingServices {

	@Autowired
	private RatingRepository ratingRepository;

	/**
	 * 
	 * <b>findAllRatings retourne l'ensemble des Rating existant dans la base de
	 * donnée.</b>
	 * 
	 * @return List<Rating>
	 */
	public List<Rating> findAllRatings() {

		return ratingRepository.findAll();
	}

	/**
	 * 
	 * <b>saveANewRating permet de sauvegarder un Rating déjà validé au préalable
	 * dans la base de donnée. </b>
	 * 
	 * @param rating
	 * @return Rating
	 */
	public Rating saveANewRating(@Valid Rating rating) {

		return ratingRepository.save(rating);
	}

	/**
	 * <b>findSpecificRating permet de récupérer un Rating spécifique en fonction
	 * d'un ID.</b>
	 * 
	 * 
	 * @param id
	 * @return Rating
	 */
	public Rating findSpecificRating(Integer id) {
		return ratingRepository.findById(id).get();
	}

	/**
	 * <b>updateAGivenRating permet de mettre à jour un Rating en fonction d'un
	 * Rating fournit par le formulaire.</b>
	 * 
	 * 
	 * @param id
	 * @return Boolean
	 */
	public Boolean updateAGivenRating(@Valid Rating rating) {

		Optional<Rating> foundItem = ratingRepository.findById(rating.getId());

		if (foundItem.isEmpty()) {

			return false;

		} else {

			ratingRepository.save(rating);
			return true;

		}
	}

	/**
	 * <b>deleteAGivenRating permet de supprimer un Rating en fonction d'un ID
	 * fournit par le formulaire.</b>
	 * 
	 * 
	 * @param id
	 * @return Boolean
	 */
	public Boolean deleteAGivenRating(Integer id) {

		Optional<Rating> foundItem = ratingRepository.findById(id);

		if (foundItem.isEmpty()) {

			return false;

		} else {

			ratingRepository.deleteById(id);
			return true;

		}
	}

	//
	//
	// Setters present solely for testing purposes, can be deleted afterwards.
	//
	//
	public void setRatingRepository(RatingRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}

}
