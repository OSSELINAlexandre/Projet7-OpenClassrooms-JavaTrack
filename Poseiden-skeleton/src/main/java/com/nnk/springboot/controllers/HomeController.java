package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointServices;

/**
 * <p>HomeController est le contrôleur en charge du domaine 'Home'.</p>
 * 
 * <p>Ce contrôleur centralise l'ensemble des points d'accueils existants pour les utilisateurs.
 * Si des interfaces utilisateurs liées aux comptes devaient être crées, ce serait dans ce contrôleur. </p>
 * 
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */
@Controller
public class HomeController {

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(HomeController.class);

	@RequestMapping("/")
	public String home(Model model) {
		return "home";
	}
	
	
	

	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		return "redirect:/user/list";
	}

}
