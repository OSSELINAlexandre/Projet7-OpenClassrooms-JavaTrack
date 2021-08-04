package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
public class RuleNameServices {
	
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(RuleNameServices.class);

	
	@Autowired
	RuleNameRepository ruleNameRepository;

	public List<RuleName> findAllRules() {
		
		
		return ruleNameRepository.findAll();
	}

	public RuleName saveANewRule(@Valid RuleName ruleName) {
		
		return ruleNameRepository.save(ruleName);
	}


	public Boolean deleteAGivenRule(Integer id) {
		
		Optional<RuleName>  foundItem = ruleNameRepository.findById(id);
		
		if(foundItem.isEmpty()) {
			
			return false;
			
		}else {
			
			ruleNameRepository.deleteById(id);
			return true;
			
		}
	}
	
	public Boolean updateAGivenRule(@Valid RuleName ruleName) {
		
		Optional<RuleName> foundItem = ruleNameRepository.findById(ruleName.getId());
		
		if(foundItem.isEmpty()) {
			
			return false;
			
		}else {
			
			ruleNameRepository.save(ruleName);
			logger.info("=====|||||====" + foundItem.get().getId());
			return true;
			
		}
	}
	
	
	public RuleName findSpecificRule(Integer id) {
		return ruleNameRepository.findById(id).get();
	}

	public void setRuleNameRepository(RuleNameRepository ruleNameRepository) {
		this.ruleNameRepository = ruleNameRepository;
	}

	
	
}
