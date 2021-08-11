package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface RatingRepository permet l'accès aux données pour les données de
 * type Rating.
 * 
 * 
 * @author Alexandre Osselin
 *
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
