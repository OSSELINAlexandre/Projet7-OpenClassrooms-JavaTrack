package com.nnk.springboot.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController{
	
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(HomeController.class);

	private static String authorizationRequestBaseUri = "oauth2/authorization";
	Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
	
	
	@Autowired
	private ClientRegistrationRepository clientRegistrationRepo;
	
	
	@RequestMapping("/")
	public String home(Model model)
	{
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		return "redirect:/bidList/list";
	}
	
	@GetMapping("/login")
	public String returnTheLoginPage(Model model) {
		
		Iterable<ClientRegistration> clientRegistration = null;
		ResolvableType type = ResolvableType.forInstance(clientRegistrationRepo).as(Iterable.class);
		
		if(type != ResolvableType.NONE && ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
			
			clientRegistration  = (Iterable<ClientRegistration>) clientRegistrationRepo;
		}
		
		clientRegistration.forEach(registration -> 
	      oauth2AuthenticationUrls.put(registration.getClientName(), 
	      authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
	    model.addAttribute("urls", oauth2AuthenticationUrls);
		
	    
		logger.info("===Should be called here.");
		
		return "login";
	}


}
