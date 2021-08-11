package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

/**
 * <b>RuleNameServices est le service permettant d'insérer la logique business
 * dans le domaine métier RuleName.</b>
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */
@Service
public class RuleNameServices {

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(RuleNameServices.class);

	@Autowired
	private RuleNameRepository ruleNameRepository;

	/**
	 * 
	 * <b>findAllRules retourne l'ensemble des RuleName existant dans la base de
	 * donnée.</b>
	 * 
	 * @return List<RuleName>
	 */
	public List<RuleName> findAllRules() {

		return ruleNameRepository.findAll();
	}

	/**
	 * 
	 * <b>saveANewRule permet de sauvegarder un RuleName déjà validé au préalable
	 * dans la base de donnée. </b>
	 * 
	 * @param ruleName
	 * @return RuleName
	 */
	public RuleName saveANewRule(@Valid RuleName ruleName) {

		return ruleNameRepository.save(ruleName);
	}
	/**
	 * <b>deleteAGivenRule permet de supprimer un RuleName en fonction d'un ID
	 * fournit par le formulaire.</b>
	 * 
	 * 
	 * @param id
	 * @return Boolean
	 */
	public Boolean deleteAGivenRule(Integer id) {

		Optional<RuleName> foundItem = ruleNameRepository.findById(id);

		if (foundItem.isEmpty()) {

			return false;

		} else {

			ruleNameRepository.deleteById(id);
			return true;

		}
	}

	/**
	 * <b>updateAGivenRule permet de mettre à jour un RuleName en fonction d'un
	 * RuleName fournit par le formulaire.</b>
	 * 
	 * 
	 * @param id
	 * @return Boolean
	 */
	public Boolean updateAGivenRule(@Valid RuleName ruleName) {

		Optional<RuleName> foundItem = ruleNameRepository.findById(ruleName.getId());

		if (foundItem.isEmpty()) {

			return false;

		} else {

			ruleNameRepository.save(ruleName);
			logger.info("=====|||||====" + foundItem.get().getId());
			return true;

		}
	}

	/**
	 * <b>findSpecificRule permet de récupérer un RuleName spécifique en fonction
	 * d'un ID.</b>
	 * 
	 * 
	 * @param id
	 * @return RuleName
	 */
	public RuleName findSpecificRule(Integer id) {
		return ruleNameRepository.findById(id).get();
	}
	
	//
	//
	// Setters present solely for testing purposes, can be deleted afterwards.
	//
	//
	public void setRuleNameRepository(RuleNameRepository ruleNameRepository) {
		this.ruleNameRepository = ruleNameRepository;
	}

}
