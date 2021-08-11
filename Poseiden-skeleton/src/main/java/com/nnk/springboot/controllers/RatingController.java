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

/**
 * <b>RatingController est le contrôleur en charge du domaine 'Rating'.</b>
 * <p>
 * Les méthodes CRUD sont les suivantes :
 * <ul>
 * <li>CREATE : validate.</li>
 * <li>READ : home (permettant de voir toutes les instances Rating) &
 * showUpdateForm (permettant de lire une instance spécifique de Rating).</li>
 * <li>UPDATE : updateRating.</li>
 * <li>DELETE : deleteRating.</li>
 * </ul>
 * </p>
 * <p>
 * Tous les templates sont entreposés dans les ressources.
 * </p>
 * 
 * @see Rating
 * @see RatingServices
 * @see src/resources/templates/rating
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */
@Controller
public class RatingController {

	/**
	 * Service lié au contrôleur RatingController.
	 */
	@Autowired
	private RatingServices ratingServices;

	/**
	 * 'home' permet d'exposer au client la liste de l'ensemble des instances
	 * 'Ratings' ayant eu lieu et enregistrés dans la base de données.
	 * 
	 * La méthode permet d'appeler le service pour récupérer depuis la base de
	 * donnée la liste en question et de l'insérer dans le modèle.
	 * 
	 * 
	 * @param model
	 * @return rating/list
	 */
	@RequestMapping("/rating/list")
	public String home(Model model) {

		model.addAttribute("listAllRating", ratingServices.findAllRatings());

		return "rating/list";
	}

	/**
	 * 'addRatingForm' permet d'appeler le formulaire assurant la création d'un
	 * nouveau 'CurvePoint'.
	 * 
	 * @param rating
	 * @return rating/add
	 */
	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
	}

	/**
	 * 'validate' [CREATE] appeler depuis le formulaire d'ajout, permet d'aiguiller
	 * en fonction d'une erreur ou non vers les pages correspondantes. Ces erreurs
	 * sont renvoyés directement depuis le template grâces aux méthodes liées aux
	 * annotations dans le modèle de Rating.
	 * 
	 * <P>
	 * <ul>
	 * <li>Si une erreur est présente (informations renvoyées directement depuis le
	 * template) nous retournons la page initiale de création d'un nouveau 'Rating'
	 * dans lequel les erreurs seront affichées.</li>
	 * 
	 * 
	 * 
	 * <li>Si aucune erreur est présente (informations renvoyées directement depuis
	 * le template),le service enregistre la nouvelle instance et l'aiguilleur
	 * redirige vers la page de présentation de l'ensemble des 'Rating'.</li>
	 * </ul>
	 * </p>
	 * 
	 * 
	 * @see Rating
	 * @param rating
	 * @param result
	 * @param model
	 * @return redirect:/rating/list
	 */
	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "rating/add";

		}

		ratingServices.saveANewRating(rating);

		return "redirect:/rating/list";
	}

	/**
	 * showUpdateForm appeler depuis la liste des instances de 'Rating' en BDD,
	 * permet à partir d'un ID d'une instance de Rating, de présenter le formulaire
	 * de modification de l'instance du Rating en question. Cette méthode permet
	 * d'ajouter au 'model' l'instance spécifique en question grâce au service.
	 * 
	 * @param id
	 * @param model
	 * @return update
	 */
	@GetMapping("/rating/update/{id}")

	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("ratingToUpdate", ratingServices.findSpecificRating(id));

		return "rating/update";

	}

	/**
	 * <p>
	 * updateRating [UPDATE] permet, depuis une instance valide de Rating, de
	 * sauvegarder une mise à jour de ladite instance dans la base de donnée.
	 * </p>
	 * <p>
	 * L'instance de Rating est considérée valable grâce aux méthodes liées aux
	 * annotations visibles dans le domaine.
	 * </p>
	 * 
	 * @see Rating
	 * 
	 * @param id
	 * @param rating
	 * @param result
	 * @param model
	 * @return redirect:/rating/list
	 */
	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {

		rating.setId(id);
		ratingServices.updateAGivenRating(rating);
		return "redirect:/rating/list";

	}

	/**
	 * <p>
	 * deleteCurve [DELETE], appeler depuis la liste, permet de supprimer une
	 * instance de Rating, spécifiée par l'ID, de la base de donnée.
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
	 * @return redirect:/rating/list
	 */
	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {

		ratingServices.deleteAGivenRating(id);

		return "redirect:/rating/list";

	}

	// SETTER solely needed for testing purposes, can be deleted without incident on
	// code.

	public void setRatingServices(RatingServices ratingServices) {
		this.ratingServices = ratingServices;
	}

}
