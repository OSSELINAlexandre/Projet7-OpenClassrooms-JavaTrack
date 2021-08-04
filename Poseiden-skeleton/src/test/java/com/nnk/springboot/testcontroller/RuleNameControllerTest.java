package com.nnk.springboot.testcontroller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RatingServices;
import com.nnk.springboot.services.RuleNameServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleNameControllerTest {
	
	
	
	@MockBean
	RuleNameServices ruleNameServices;
	
	@MockBean
	Model model;
	
	@MockBean
	BindingResult bidingResult;
	
	private RuleNameController ruleNameController;
	
	
	
	@Before()
	public void init() {
		ruleNameController = new RuleNameController();
		ruleNameController.setRuleNameServices(ruleNameServices);

	}
	
	
	@Test
	public void test_Home() {
		
		when(ruleNameServices.findAllRules()).thenReturn(new ArrayList<RuleName>());
		
		String actual = ruleNameController.home(model);
		
		assertTrue(actual.equals("ruleName/list"));
	}
	
	@Test
	public void test_addRuleForm() {
		
		String actual = ruleNameController.addRuleForm(new RuleName());		
		assertTrue(actual.equals("ruleName/add"));

	}
	
	@Test
	public void test_validate_ShouldReturnBidList() {
		
		
		when(bidingResult.hasErrors()).thenReturn(false);
		
		String actual = ruleNameController.validate(new RuleName(), bidingResult, model);
		
		assertTrue(actual.equals("redirect:/ruleName/list"));
		
	}

	
	@Test
	public void test_validateWithErrorShouldReturnAddPage() {
		
		
		when(bidingResult.hasErrors()).thenReturn(true);
		
		String actual = ruleNameController.validate(new RuleName(), bidingResult, model);
		
		assertTrue(actual.equals("ruleName/add"));
		
	}
	
	@Test
	public void test_showUpdateForm_ShouldReturnUpdate() {
		
		when(ruleNameServices.findSpecificRule(2)).thenReturn(new RuleName());
		
		String actual = ruleNameController.showUpdateForm(2 , model);

		assertTrue(actual.equals("ruleName/update"));

		
	}
	
	@Test
	public void test_updateRuleName() {
		
		 
		RuleName testItem = new RuleName();
		
		when(ruleNameServices.updateAGivenRule(testItem)).thenReturn(true);

		
		String actual = ruleNameController.updateRuleName(1, testItem, bidingResult, model);
		
		
		assertTrue(actual.equals("redirect:/ruleName/list"));

	}
	
	
	@Test
	public void test_deleteRuleName() {
		
		String actual = ruleNameController.deleteRuleName(1, model);
		
		assertTrue(actual.equals("redirect:/ruleName/list"));

		
	}


}
