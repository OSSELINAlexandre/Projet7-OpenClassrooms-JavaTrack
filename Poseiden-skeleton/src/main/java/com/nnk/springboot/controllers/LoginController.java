package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <b>LoginController est le contrôleur en charge du domaine 'Login'.</b>
 * <p>
 * Les buts de ce contrôleur sont les suivants :
 * <ul>
 * <li>Recevoir la requête d'authentification</li>
 * <li>Aiguiller vers les méthodes adéquates en fonction de la nature de
 * l'authentification.</li>
 * <li>Créer des variables de sessions si besoin (dans notre cas, nous
 * récupérons simplement le Login de l'utilisateur pour l'afficher dans
 * l'application.</li>
 * </ul>
 * </p>
 * <p>
 * Tous les templates sont entreposés dans les ressources.
 * </p>
 * 
 * @see SpringSecurityConfig
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */
@Controller
public class LoginController {

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(LoginController.class);

	private static String authorizationRequestBaseUri = "oauth2/authorization";

	Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

	@Autowired
	private ClientRegistrationRepository clientRegistrationRepo;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/UserCredential")
	public String getCredentials(Principal user, HttpSession session) {

		StringBuffer userInfo = new StringBuffer();

		if (user instanceof UsernamePasswordAuthenticationToken) {

			userInfo.append(getUsernamePasswordLoginInfo(user));
			session.setAttribute("userName", userInfo);

		} else if (user instanceof OAuth2AuthenticationToken) {

			userInfo.append(getOauth2LoginInfo(user));
			session.setAttribute("userName", userInfo);

		}

		return "redirect:/bidList/list";

	}

//	Méthode publique pour raisons de tests, peut être privée une fois les tests réalisées.
	/**
	 * <p>
	 * La méthode getUsernamePasswordLoginInfo prend en argument le 'principal'
	 * provenant de l'authentification par session.
	 * </p>
	 * 
	 * <p>
	 * Dans cette méthode, nous récupérons les informations provenant de ladite
	 * authentification.
	 * </p>
	 * 
	 * 
	 * @param user
	 * @return
	 */
	public StringBuffer getUsernamePasswordLoginInfo(Principal user) {

		StringBuffer usernameInfo = new StringBuffer();

		UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);

		if (token.isAuthenticated()) {
			User u = (User) token.getPrincipal();
			usernameInfo.append(u.getUsername());
		} else {
			usernameInfo.append("NA");
		}
		return usernameInfo;
	}

//	Méthode publique pour raisons de tests, peut être privée une fois les tests réalisées.

	/**
	 * <p>
	 * La méthode getUsernamePasswordLoginInfo prend en argument le 'principal'
	 * provenant de l'authentification par token OAuth.
	 * </p>
	 * 
	 * <p>
	 * Dans cette méthode, nous récupérons les informations provenant de ladite
	 * authentification.
	 * </p>
	 * 
	 * @param user
	 * @return
	 */
	public StringBuffer getOauth2LoginInfo(Principal user) {

		StringBuffer protectedInfo = new StringBuffer();

		OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);

		if (authToken.isAuthenticated()) {

			Map<String, Object> userAttributes = ((DefaultOAuth2User) authToken.getPrincipal()).getAttributes();

			protectedInfo.append(userAttributes.get("login"));

		} else {

			protectedInfo.append("NA");

		}

		return protectedInfo;
	}

	/**
	 * 
	 * <b>returnTheLoginPage est notre méthode permettant l'accès au formulaire
	 * d'authentification.</b>
	 * 
	 * <p>
	 * La méthode prend Model comme argument afin de fixer les différentes méthodes
	 * OAuth d'authentification.
	 * </p>
	 * 
	 * 
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/login")
	public String returnTheLoginPage(Model model) {

		Iterable<ClientRegistration> clientRegistration = null;
		ResolvableType type = ResolvableType.forInstance(clientRegistrationRepo).as(Iterable.class);

		if (type != ResolvableType.NONE && ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {

			clientRegistration = (Iterable<ClientRegistration>) clientRegistrationRepo;
		}

		clientRegistration.forEach(registration -> oauth2AuthenticationUrls.put(registration.getClientName(),
				authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
		model.addAttribute("urls", oauth2AuthenticationUrls);

		return "login";
	}

	@GetMapping("secure/article-details")
	public ModelAndView getAllUserArticles() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", userRepository.findAll());
		mav.setViewName("user/list");
		return mav;
	}

	@GetMapping("error")
	public ModelAndView error() {
		ModelAndView mav = new ModelAndView();
		String errorMessage = "You are not authorized for the requested data.";
		mav.addObject("errorMsg", errorMessage);
		mav.setViewName("403");
		return mav;
	}
}
