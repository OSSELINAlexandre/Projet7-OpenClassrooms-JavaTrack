package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListServices;

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
 * <b>BidListController est le controleur en charge du domaine 'BidList'.</b>
 * <p>
 * Les méthodes CRUD sont les suivantes :
 * <ul>
 * <li>CREATE : validate.</li>
 * <li>READ : home (permettant de voire toutes les instances BidList) &
 * showUpdateForm (permettant de lire une instance spécifique de BidList).</li>
 * <li>UPDATE : updateBid.</li>
 * <li>DELETE : deleteBid.</li>
 * </ul>
 * </p>
 * <p>
 * Tous les templates sont entreposés dans les ressources.
 * </p>
 * 
 * @see BidList
 * @see BidListServices
 * @see src/resources/templates/bidList
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */
@Controller
public class BidListController {

	/**
	 * Service lié au contrôleur BidListController.
	 */
	@Autowired
	private BidListServices bidListServices;

	/**
	 * 'home' permet d'exposer au client la liste de l'ensemble des instances
	 * de'BidsList' ayant eu lieu et enregistrés dans la base de données.
	 * 
	 * La méthode permet d'appeler le service pour récupérer depuis la base de
	 * donnée la liste en question et de l'insérer dans le modèle.
	 * 
	 * 
	 * @param model
	 * @return bidList/list
	 */
	@RequestMapping("/bidList/list")
	public String home(Model model) {

		model.addAttribute("listAllBids", bidListServices.findAllBids());
		return "bidList/list";
	}

	/**
	 * 'addBidForm' permet d'appeler le formulaire assurant la création d'un nouveau
	 * 'Bid'.
	 * 
	 * @param bid
	 * @return bidList/add
	 */
	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		return "bidList/add";
	}

	/**
	 * 'validate' [CREATE] appeler depuis le formulaire d'ajout, permet d'aiguiller
	 * en fonction d'une erreur ou non vers les pages correspondantes. Ces erreurs
	 * sont renvoyés directement depuis le template grâces aux méthodes liées aux
	 * annotations dans le modèle de BidList.
	 * 
	 * <P>
	 * <ul>
	 * <li>Si une erreur est présente (informations renvoyées directement depuis le
	 * template) nous retournons la page initiale de création d'un nouveau 'Bid'
	 * dans lequel les erreurs seront affichées.</li>
	 * 
	 * 
	 * 
	 * <li>Si aucune erreur est présente (informations renvoyées directement depuis
	 * le template),le service enregistre la nouvelle instance et l'aiguilleur
	 * redirige vers la page de présentation de l'ensemble des 'Bid'.</li>
	 * </ul>
	 * </p>
	 * 
	 * 
	 * @see BidList
	 * @param bid
	 * @param result
	 * @param model
	 * @return redirect:/bidList/list
	 */
	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "bidList/add";

		}

		bidListServices.saveANewBid(bid);

		return "redirect:/bidList/list";
	}

	/**
	 * showUpdateForm appeler depuis la liste des instances de 'BidList' en BDD,
	 * permet à partir d'un ID d'une instance de BidList, de présenter le formulaire
	 * de modification de l'instance du Bid en question. Cette méthode permet
	 * d'ajouter au 'model' l'instance spécifique en question grâce au service.
	 * 
	 * @param id
	 * @param model
	 * @return update
	 */
	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("bidListToUpdate", bidListServices.findSpecificBid(id));

		return "bidList/update";
	}

	/**
	 * <p>
	 * updateBid [UPDATE] permet, depuis une instance valide de Bid, de sauvegarder
	 * une mise à jour de ladite instance dans la base de donnée.
	 * </p>
	 * <p>
	 * L'instance de BidList est considérée valable grâces aux méthodes liées aux
	 * annotations visibles dans le domaine.
	 * </p>
	 * 
	 * @see BidList
	 * 
	 * @param id
	 * @param bidList
	 * @param result
	 * @param model
	 * @return redirect:/bidList/list
	 */
	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {

		bidList.setBidlistid(id);
		bidListServices.updateAGivenBid(bidList);

		return "redirect:/bidList/list";

	}

	/**
	 * <p>
	 * deleteBid [DELETE], appeler depuis la liste, permet de supprimer une instance
	 * de BidList, spécifiée par l'ID, de la base de donnée.
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
	 * @return redirect:/bidList/list
	 */
	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {

		bidListServices.deleteAGivenBid(id);

		return "redirect:/bidList/list";

	}

	// SETTER solely needed for testing purposes, can be deleted without incident on
	// code.

	public void setBidListServices(BidListServices bidListServices) {
		this.bidListServices = bidListServices;
	}

}
