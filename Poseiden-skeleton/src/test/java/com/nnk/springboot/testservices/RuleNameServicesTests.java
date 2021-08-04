package com.nnk.springboot.testservices;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.BidListServices;
import com.nnk.springboot.services.RuleNameServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleNameServicesTests {
	
	
	@MockBean
	RuleNameRepository ruleNameRepository; 


	private RuleNameServices ruleNameServices;
	
	private RuleName testingItem;
	
	@Before()
	public void init() {
		
		ruleNameServices = new RuleNameServices();
		ruleNameServices.setRuleNameRepository(ruleNameRepository);

		
	}
	
	
	@Test
	public void test_updateAGivenRule_ShouldReturnTrueIfExist() {
		
		
		Optional<RuleName> itemOp = Optional.ofNullable(new RuleName());
		
		
		RuleName testItem = new RuleName();
		testItem.setId(5);
		
		when(ruleNameRepository.findById(5)).thenReturn(itemOp);
		
		
		Boolean actual = ruleNameServices.updateAGivenRule(testItem);
		
		assertTrue(actual == true);
		
	}
	
	@Test
	public void test_updateAGivenRule_ShouldReturnFalseIfDoNotExist() {
		

		
		
		Optional<RuleName> itemOp = Optional.empty();
				
		when(ruleNameRepository.findById(5)).thenReturn(itemOp);
		
		RuleName testItem = new RuleName();
		testItem.setId(5);
				
		Boolean actual = ruleNameServices.updateAGivenRule(testItem);
		
		assertTrue(actual == false);
		
	}
	
	
	@Test
	public void test_deleteAGivenRule_ShouldReturnTrueIfExist() {
		
		
		Optional<RuleName> itemOp = Optional.ofNullable(new RuleName());
		

		
		when(ruleNameRepository.findById(5)).thenReturn(itemOp);
		
		
		Boolean actual = ruleNameServices.deleteAGivenRule(5);
		
		assertTrue(actual == true);
		
	}
	
	@Test
	public void test_deleteAGivenRule_ShouldReturnFalseIfDoNotExist() {
		
		
		
		Optional<RuleName> itemOp = Optional.empty();
				
		when(ruleNameRepository.findById(5)).thenReturn(itemOp);

				
		Boolean actual = ruleNameServices.deleteAGivenRule(5);
		
		assertTrue(actual == false);
		
	}


}
