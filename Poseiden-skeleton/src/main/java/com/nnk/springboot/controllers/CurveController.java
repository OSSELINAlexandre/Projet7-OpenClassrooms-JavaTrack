package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointServices;

import org.apache.logging.log4j.LogManager;
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
 * <b>CurveController est le contrôleur en charge du domaine 'CurvePoint'.</b>
 * <p>
 * Les méthodes CRUD sont les suivantes :
 * <ul>
 * <li>CREATE : validate.</li>
 * <li>READ : home (permettant de voir toutes les instances CurvePoint) &
 * showUpdateForm (permettant de lire une instance spécifique de
 * CurvePoint).</li>
 * <li>UPDATE : updateCurve.</li>
 * <li>DELETE : deleteCurve.</li>
 * </ul>
 * </p>
 * <p>
 * Tous les templates sont entreposés dans les ressources.
 * </p>
 * 
 * @see CurvePoint
 * @see CurvePointServices
 * @see src/resources/templates/curvePoint
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */
@Controller
public class CurveController {

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CurveController.class);

	/**
	 * Service lié au contrôleur CurveController.
	 */
	@Autowired
	private CurvePointServices curvePointServices;

	/**
	 * 'home' permet d'exposer au client la liste de l'ensemble des instances de
	 * 'CurvePoint' ayant eu lieu et enregistrés dans la base de données.
	 * 
	 * La méthode permet d'appeler le service pour récupérer depuis la base de
	 * donnée la liste en question et de l'insérer dans le modèle.
	 * 
	 * 
	 * @param model
	 * @return curvePoint/list
	 */
	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		model.addAttribute("listAllCurvePoint", curvePointServices.findAllCurvePoints());

		return "curvePoint/list";
	}

	/**
	 * 'addCurveForm' permet d'appeler le formulaire assurant la création d'un
	 * nouveau 'CurvePoint'.
	 * 
	 * @param curve
	 * @return curvePoint/add
	 */
	@GetMapping("/curvePoint/add")
	public String addCurveForm(CurvePoint curve) {
		return "curvePoint/add";
	}

	/**
	 * 'validate' [CREATE] appeler depuis le formulaire d'ajout, permet d'aiguiller
	 * en fonction d'une erreur ou non vers les pages correspondantes. Ces erreurs
	 * sont renvoyés directement depuis le template grâces aux méthodes liées aux
	 * annotations dans le modèle de CurvePoint.
	 * 
	 * <P>
	 * <ul>
	 * <li>Si une erreur est présente (informations renvoyées directement depuis le
	 * template) nous retournons la page initiale de création d'un nouveau
	 * 'CurvePoint' dans lequel les erreurs seront affichées.</li>
	 * 
	 * 
	 * 
	 * <li>Si aucune erreur est présente (informations renvoyées directement depuis
	 * le template),le service enregistre la nouvelle instance et l'aiguilleur
	 * redirige vers la page de présentation de l'ensemble des 'CurvePoint'.</li>
	 * </ul>
	 * </p>
	 * 
	 * 
	 * @see CurvePoint
	 * @param curvePoint
	 * @param result
	 * @param model
	 * @return redirect:/curvePoint/list
	 */
	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "curvePoint/add";

		}

		curvePointServices.saveANewCurvedPoint(curvePoint);

		return "redirect:/curvePoint/list";
	}

	/**
	 * showUpdateForm appeler depuis la liste des instances de 'CurvePoint' en BDD,
	 * permet à partir d'un ID d'une instance de CurvePoint, de présenter le
	 * formulaire de modification de l'instance du curvePoint en question. Cette
	 * méthode permet d'ajouter au 'model' l'instance spécifique en question grâce
	 * au service.
	 * 
	 * @param id
	 * @param model
	 * @return update
	 */
	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		logger.info("=========== WTC =============" + id);
		model.addAttribute("curvePointToUpdate", curvePointServices.findSpecificCurvePoint(id));

		return "curvePoint/update";
	}

	/**
	 * <p>
	 * updateCurve [UPDATE] permet, depuis une instance valide de CurvePoint, de
	 * sauvegarder une mise à jour de ladite instance dans la base de donnée.
	 * </p>
	 * <p>
	 * L'instance de CurvePoint est considérée valable grâce aux méthodes liées aux
	 * annotations visibles dans le domaine.
	 * </p>
	 * 
	 * @see CurvePoint
	 * 
	 * @param id
	 * @param curvePoint
	 * @param result
	 * @param model
	 * @return redirect:/curvePoint/list
	 */
	@PostMapping("/curvePoint/update/{id}")
	public String updateCurve(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {

		if (result.hasErrors()) {

			return "curvePoint/update";

		}
		curvePoint.setId(id);
		curvePointServices.updateAGivenCurvePoint(curvePoint);

		return "redirect:/curvePoint/list";

	}

	/**
	 * <p>
	 * deleteCurve [DELETE], appeler depuis la liste, permet de supprimer une
	 * instance de CurvePoint, spécifiée par l'ID, de la base de donnée.
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
	 * @return redirect:/curvePoint/list
	 */
	@GetMapping("/curvePoint/delete/{id}")
	public String deleteCurve(@PathVariable("id") Integer id, Model model) {

		curvePointServices.deleteAGivenCurvePoint(id);

		return "redirect:/curvePoint/list";

	}

	// SETTER solely needed for testing purposes, can be deleted without incident on
	// code.

	public void setCurvePointServices(CurvePointServices curvePointServices) {
		this.curvePointServices = curvePointServices;
	}

}
