package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.RatingServices;
import com.nnk.springboot.services.UserServices;
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

/**
 * <b>UserController est le controleur en charge du domaine 'User'.</b>
 * <p>
 * Les méthodes CRUD sont les suivantes :
 * <ul>
 * <li>CREATE : validate.</li>
 * <li>READ : home (permettant de voire toutes les instances RuleName) &
 * showUpdateForm (permettant de lire une instance spécifique de RuleName).</li>
 * <li>UPDATE : updateRuleName.</li>
 * <li>DELETE : deleteRuleName.</li>
 * </ul>
 * </p>
 * <p>
 * Tous les templates sont entreposés dans les ressources.
 * </p>
 * 
 * @see User
 * @see UserServices
 * @see src/resources/templates/user
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */
@Controller
public class UserController {

	/**
	 * Service lié au contrôleur UserController.
	 */
	@Autowired
	private UserServices userServices;

	/**
	 * 'home' permet d'exposer au client la liste de l'ensemble des instances de
	 * 'User' ayant eu lieu et enregistrés dans la base de données.
	 * 
	 * La méthode permet d'appeler le service pour récupérer depuis la base de
	 * donnée la liste en question et de l'insérer dans le modèle.
	 * 
	 * 
	 * @param model
	 * @return user/list
	 */
	@RequestMapping("/user/list")
	public String home(Model model) {

		model.addAttribute("users", userServices.findAllUsers());
		return "user/list";

	}

	/**
	 * 'addUser' permet d'appeler le formulaire assurant la création d'un nouveau
	 * 'User'.
	 * 
	 * @param user
	 * @return user/add
	 */
	@GetMapping("/user/add")
	public String addUser(User user) {
		return "user/add";
	}

	/**
	 * 'validate' [CREATE] appeler depuis le formulaire d'ajout, permet d'aiguiller
	 * en fonction d'une erreur ou non vers les pages correspondantes. Ces erreurs
	 * sont renvoyés directement depuis le template grâces aux méthodes liées aux
	 * annotations dans le modèle de RuleName.
	 * 
	 * <P>
	 * <ul>
	 * <li>Si une erreur est présente (informations renvoyées directement depuis le
	 * template) nous retournons la page initiale de création d'un nouveau 'User'
	 * dans lequel les erreurs seront affichées.</li>
	 * 
	 * 
	 * 
	 * <li>Si aucune erreur est présente (informations renvoyées directement depuis
	 * le template),,le service enregistre la nouvelle instance avec le cryptage
	 * d'un mot de passe l'aiguilleur redirige vers la page de présentation de
	 * l'ensemble des 'User'.</li>
	 * </ul>
	 * </p>
	 * 
	 * 
	 * @see User
	 * @param user
	 * @param result
	 * @param model
	 * @return redirect:/user/list
	 */
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

	/**
	 * showUpdateForm appeler depuis la liste des instances de 'User' en BDD, permet
	 * à partir d'un ID d'une instance de User, de présenter le formulaire de
	 * modification de l'instance du User en question. Cette méthode permet
	 * d'ajouter au 'model' l'instance spécifique en question grâce au service.
	 * 
	 * @param id
	 * @param model
	 * @return update
	 */
	@GetMapping("/user/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("user", userServices.findSpecificUser(id));

		return "user/update";
	}

	/**
	 * <p>
	 * updateRuleName [UPDATE] permet, depuis une instance valide de User, de
	 * sauvegarder une mise à jour de ladite instance dans la base de donnée.
	 * </p>
	 * <p>
	 * L'instance de User est considérée valable grâces aux méthodes liées aux
	 * annotations visibles dans le domaine.
	 * </p>
	 * 
	 * @see User
	 * 
	 * @param id
	 * @param user
	 * @param result
	 * @param model
	 * @return redirect:/user/list
	 */
	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {

		user.setId(id);
		userServices.updateAGivenUser(user);

		return "redirect:/user/list";

	}

	/**
	 * <p>
	 * deleteCurve [DELETE], appeler depuis la liste, permet de supprimer une
	 * instance de User, spécifiée par l'ID, de la base de donnée.
	 * </p>
	 *
	 * <p>
	 * Seuls des instances valables sont déjà enregistrés dans la base de donnée et
	 * afficher dans la liste, ainsi l'instance spécifiée par l'ID et devant être
	 * supprimé est de ce fait valable.
	 * </p>
	 * 
	 * 
	 * @param id
	 * @param model
	 * @return redirect:/ruleName/list
	 */
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {

		userServices.deleteAGivenUser(id);

		return "redirect:/user/list";
	}

	// SETTER solely needed for testing purposes, can be deleted without incident on
	// code.
	public void setUserServices(UserServices userServices) {
		this.userServices = userServices;
	}

}
