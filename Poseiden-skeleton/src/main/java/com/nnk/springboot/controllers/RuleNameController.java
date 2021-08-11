package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameServices;

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
 * <b>RuleNameController est le contrôleur en charge du domaine 'RuleName'.</b>
 * <p>
 * Les méthodes CRUD sont les suivantes :
 * <ul>
 * <li>CREATE : validate.</li>
 * <li>READ : home (permettant de voir toutes les instances RuleName) &
 * showUpdateForm (permettant de lire une instance spécifique de RuleName).</li>
 * <li>UPDATE : updateRuleName.</li>
 * <li>DELETE : deleteRuleName.</li>
 * </ul>
 * </p>
 * <p>
 * Tous les templates sont entreposés dans les ressources.
 * </p>
 * 
 * @see RuleName
 * @see RuleNameServices
 * @see src/resources/templates/ruleName
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */
@Controller
public class RuleNameController {

	/**
	 * Service lié au contrôleur RuleNameController.
	 */
	@Autowired
	private RuleNameServices ruleNameServices;

	/**
	 * 'home' permet d'exposer au client la liste de l'ensemble des instances de
	 * 'RuleName' ayant eu lieu et enregistrés dans la base de données.
	 * 
	 * La méthode permet d'appeler le service pour récupérer depuis la base de
	 * donnée la liste en question et de l'insérer dans le modèle.
	 * 
	 * 
	 * @param model
	 * @return ruleName/list
	 */
	@RequestMapping("/ruleName/list")
	public String home(Model model) {
		model.addAttribute("listAllRules", ruleNameServices.findAllRules());
		return "ruleName/list";
	}

	/**
	 * 'addRuleForm' permet d'appeler le formulaire assurant la création d'un
	 * nouveau 'RuleName'.
	 * 
	 * @param rulename
	 * @return ruleName/add
	 */
	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName rulename) {
		return "ruleName/add";
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
	 * template) nous retournons la page initiale de création d'un nouveau
	 * 'RuleName' dans lequel les erreurs seront affichées.</li>
	 * 
	 * 
	 * 
	 * <li>Si aucune erreur est présente (informations renvoyées directement depuis
	 * le template), ,le service enregistre la nouvelle instance etl'aiguilleur
	 * redirige vers la page de présentation de l'ensemble des 'RuleName'.</li>
	 * </ul>
	 * </p>
	 * 
	 * 
	 * @see RuleName
	 * @param ruleName
	 * @param result
	 * @param model
	 * @return redirect:/ruleName/list
	 */
	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "ruleName/add";

		}

		ruleNameServices.saveANewRule(ruleName);

		return "redirect:/ruleName/list";
	}

	/**
	 * showUpdateForm appeler depuis la liste des instances de 'RuleName' en BDD,
	 * permet à partir d'un ID d'une instance de RuleName, de présenter le
	 * formulaire de modification de l'instance du RuleName en question. Cette
	 * méthode permet d'ajouter au 'model' l'instance spécifique en question grâce
	 * au service.
	 * 
	 * @param id
	 * @param model
	 * @return update
	 */
	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("RuleToUpdate", ruleNameServices.findSpecificRule(id));

		return "ruleName/update";
	}

	/**
	 * <p>
	 * updateRuleName [UPDATE] permet, depuis une instance valide de RuleName, de
	 * sauvegarder une mise à jour de ladite instance dans la base de donnée.
	 * </p>
	 * <p>
	 * L'instance de RuleName est considérée valable grâce aux méthodes liées aux
	 * annotations visibles dans le domaine.
	 * </p>
	 * 
	 * @see RuleName
	 * 
	 * @param id
	 * @param ruleName
	 * @param result
	 * @param model
	 * @return redirect:/ruleName/list
	 */
	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) {

		ruleName.setId(id);
		ruleNameServices.updateAGivenRule(ruleName);

		return "redirect:/ruleName/list";

	}

	/**
	 * <p>
	 * deleteCurve [DELETE], appeler depuis la liste, permet de supprimer une
	 * instance de RuleName, spécifiée par l'ID, de la base de donnée.
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
	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {

		ruleNameServices.deleteAGivenRule(id);

		return "redirect:/ruleName/list";

	}

	// SETTER solely needed for testing purposes, can be deleted without incident on
	// code.

	public void setRuleNameServices(RuleNameServices ruleNameServices) {
		this.ruleNameServices = ruleNameServices;
	}

}
