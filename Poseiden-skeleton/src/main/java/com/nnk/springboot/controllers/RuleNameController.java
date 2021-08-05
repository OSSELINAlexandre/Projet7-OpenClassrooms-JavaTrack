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

@Controller
public class RuleNameController {


	@Autowired
	RuleNameServices ruleNameServices;

	
	//READ
	@RequestMapping("/ruleName/list")
	public String home(Model model) {
		model.addAttribute("listAllRules", ruleNameServices.findAllRules());
		return "ruleName/list";
	}

	

	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName bid) {
		return "ruleName/add";
	}

    //CREATE
	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "ruleName/add";

		}

		ruleNameServices.saveANewRule(ruleName);

		return "redirect:/ruleName/list";
	}

	
    //READ
	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("RuleToUpdate", ruleNameServices.findSpecificRule(id));

		return "ruleName/update";
	}

	
	
    //UPDATE
	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) {

		ruleName.setId(id);
		ruleNameServices.updateAGivenRule(ruleName);

		return "redirect:/ruleName/list";


	}
	
    //DELETE
	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {

		ruleNameServices.deleteAGivenRule(id);

		return "redirect:/ruleName/list";

	}


    //SETTER solely needed for testing purposes, can be deleted without incident on code.

	public void setRuleNameServices(RuleNameServices ruleNameServices) {
		this.ruleNameServices = ruleNameServices;
	}
	
	
	
}
