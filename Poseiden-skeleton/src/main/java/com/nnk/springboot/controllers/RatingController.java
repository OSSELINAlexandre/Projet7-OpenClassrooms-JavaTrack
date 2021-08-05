package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;

@Controller
public class RatingController {


	
	@Autowired
	RatingServices ratingServices;

	
	//READ
	@RequestMapping("/rating/list")
	public String home(Model model) {

		model.addAttribute("listAllRating", ratingServices.findAllRatings());

		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
	}
	
    //CREATE
	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "rating/add";

		}

		
		ratingServices.saveANewRating(rating);

		return "redirect:/rating/list";
	}

    //READ
	@GetMapping("/rating/update/{id}")

	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {


		model.addAttribute("ratingToUpdate", ratingServices.findSpecificRating(id));
		
		return "rating/update";
		
	}

    //UPDATE
	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {

		rating.setId(id);
		ratingServices.updateAGivenRating(rating);
		return "redirect:/rating/list";


	}

    //DELETE
	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {

		ratingServices.deleteAGivenRating(id);

		return "redirect:/rating/list";

	}
	
    //SETTER solely needed for testing purposes, can be deleted without incident on code.

	public void setRatingServices(RatingServices ratingServices) {
		this.ratingServices = ratingServices;
	}
	
	
	
}
