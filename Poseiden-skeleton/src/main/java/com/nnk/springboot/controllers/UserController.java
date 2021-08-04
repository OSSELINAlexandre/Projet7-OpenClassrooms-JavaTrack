package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserServices;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {
	
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(UserController.class);


	@Autowired
	private UserServices userServices;

	@RequestMapping("/user/list")
	public String home(Model model) {
		
		model.addAttribute("users", userServices.findAllUsers());
		return "user/list";
		
	}

	@GetMapping("/user/add")
	public String addUser(User bid) {
		return "user/add";
	}

	@PostMapping("/user/validate")
	public String validate(@Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "user/add";

		} else {

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			userServices.saveANewUser(user);
			return "redirect:/user/list";
		}

	}

	@GetMapping("/user/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("user", userServices.findSpecificUser(id));

		return "user/update";
	}

	@PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
    
    	user.setId(id);
		userServices.updateAGivenUser(user);

	    return "redirect:/user/list";
	
    }

	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		
		userServices.deleteAGivenUser(id);
		
		
		return "redirect:/user/list";
	}

	public void setUserServices(UserServices userServices) {
		this.userServices = userServices;
	}
	
	
	
}
