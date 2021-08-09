package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.RatingServices;
import com.nnk.springboot.services.TradeServices;

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
 * <b>TradeController est le controleur en charge du domaine 'Trade'.</b>
 * <p>
 * Les méthodes CRUD sont les suivantes :
 * <ul>
 * <li>CREATE : validate.</li>
 * <li>READ : home (permettant de voire toutes les instances Trade) &
 * showUpdateForm (permettant de lire une instance spécifique de Trade).</li>
 * <li>UPDATE : updateTrade.</li>
 * <li>DELETE : deleteTrade.</li>
 * </ul>
 * </p>
 * <p>
 * Tous les templates sont entreposés dans les ressources.
 * </p>
 * 
 * @see Trade
 * @see TradeServices
 * @see src/resources/templates/trade
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */
@Controller
public class TradeController {

	/**
	 * Service lié au contrôleur TradeController.
	 */
	@Autowired
	private TradeServices tradeServices;

	/**
	 * 'home' permet d'exposer au client la liste de l'ensemble des instances de
	 * 'Trade' ayant eu lieu et enregistrés dans la base de données.
	 * 
	 * La méthode permet d'appeler le service pour récupérer depuis la base de
	 * donnée la liste en question et de l'insérer dans le modèle.
	 * 
	 * 
	 * @param model
	 * @return trade/list
	 */
	@RequestMapping("/trade/list")
	public String home(Model model) {
		model.addAttribute("listAllTrades", tradeServices.findAllTrades());

		return "trade/list";
	}

	/**
	 * 'addTrade' permet d'appeler le formulaire assurant la création d'un nouveau
	 * 'Trade'.
	 * 
	 * @param trade
	 * @return trade/add
	 */
	@GetMapping("/trade/add")
	public String addTrade(Trade trade) {
		return "trade/add";
	}

	/**
	 * 'validate' [CREATE] appeler depuis le formulaire d'ajout, permet d'aiguiller
	 * en fonction d'une erreur ou non vers les pages correspondantes. Ces erreurs
	 * sont renvoyés directement depuis le template grâces aux méthodes liées aux
	 * annotations dans le modèle de Trade.
	 * 
	 * <P>
	 * <ul>
	 * <li>Si une erreur est présente (informations renvoyées directement depuis le
	 * template) nous retournons la page initiale de création d'un nouveau 'Trade'
	 * dans lequel les erreurs seront affichées.</li>
	 * 
	 * 
	 * 
	 * <li>Si aucune erreur est présente (informations renvoyées directement depuis
	 * le template) l'aiguilleur redirige vers la page de présentation de l'ensemble
	 * des 'Trade'.</li>
	 * </ul>
	 * </p>
	 * 
	 * 
	 * @see Trade
	 * @param trade
	 * @param result
	 * @param model
	 * @return redirect:/trade/list
	 */
	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "trade/add";

		}

		trade = tradeServices.setIfTradeIfASellOrABuy(trade);
		tradeServices.saveANewTrade(trade);

		return "redirect:/trade/list";

	}

	/**
	 * showUpdateForm appeler depuis la liste des instances de 'Trade' en BDD,
	 * permet à partir d'un ID d'une instance de Trade, de présenter le formulaire
	 * de modification de l'instance du Trade en question. Cette méthode permet
	 * d'ajouter au 'model' l'instance spécifique en question grâce au service.
	 * 
	 * @param id
	 * @param model
	 * @return update
	 */
	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("tradeToUpdate", tradeServices.findSpecificTrade(id));

		return "trade/update";
	}

	/**
	 * <p>
	 * updateRuleName [UPDATE] permet, depuis une instance valide de Trade, de
	 * sauvegarder une mise à jour de ladite instance dans la base de donnée.
	 * </p>
	 * <p>
	 * L'instance de Trade est considérée valable grâces aux méthodes liées aux
	 * annotations visibles dans le domaine.
	 * </p>
	 * 
	 * @see Trade
	 * 
	 * @param id
	 * @param trade
	 * @param result
	 * @param model
	 * @return redirect:/trade/list
	 */
	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {

		trade.setTradeId(id);

		tradeServices.updateAGivenTrade(trade);

		return "redirect:/trade/list";

	}

	/**
	 * <p>
	 * deleteCurve [DELETE], appeler depuis la liste, permet de supprimer une
	 * instance de Trade, spécifiée par l'ID, de la base de donnée.
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
	 * @return redirect:/trade/list
	 */
	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {

		tradeServices.deleteAGivenTrade(id);

		return "redirect:/trade/list";

	}

	// SETTER solely needed for testing purposes, can be deleted without incident on
	// code.

	public void setTradeServices(TradeServices tradeServices) {
		this.tradeServices = tradeServices;
	}

}
