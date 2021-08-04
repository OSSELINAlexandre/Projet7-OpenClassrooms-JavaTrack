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
import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeControllerTest {
	
	
	
	@MockBean
	TradeServices tradeServices;
	
	@MockBean
	Model model;
	
	@MockBean
	BindingResult bidingResult;
	
	private TradeController tradeController;
	
	
	
	@Before()
	public void init() {
		tradeController = new TradeController();
		tradeController.setTradeServices(tradeServices);

	}
	
	
	@Test
	public void test_Home() {
		
		when(tradeServices.findAllTrades()).thenReturn(new ArrayList<Trade>());
		
		String actual = tradeController.home(model);
		
		assertTrue(actual.equals("trade/list"));
	}
	
	@Test
	public void test_addRatingForm() {
		
		String actual = tradeController.addTrade(new Trade());		
		assertTrue(actual.equals("trade/add"));

	}
	
	@Test
	public void test_validate_ShouldReturnBidList() {
		
		
		when(bidingResult.hasErrors()).thenReturn(false);
		
		String actual = tradeController.validate(new Trade(), bidingResult, model);
		
		assertTrue(actual.equals("redirect:/trade/list"));
		
	}

	
	@Test
	public void test_validateWithErrorShouldReturnAddPage() {
		
		
		when(bidingResult.hasErrors()).thenReturn(true);
		
		String actual = tradeController.validate(new Trade(), bidingResult, model);
		
		assertTrue(actual.equals("redirect:/trade/add"));
		
	}
	
	@Test
	public void test_showUpdateForm_ShouldReturnUpdate() {
		
		when(tradeServices.findSpecificTrade(2)).thenReturn(new Trade());
		
		String actual = tradeController.showUpdateForm(2 , model);

		assertTrue(actual.equals("trade/update"));

		
	}
	
	@Test
	public void test_updateRating() {
		
		 
		Trade testItem = new Trade();
		
		when(tradeServices.updateAGivenTrade(testItem)).thenReturn(true);

		
		String actual = tradeController.updateTrade(1, testItem, bidingResult, model);
		
		
		assertTrue(actual.equals("redirect:/trade/list"));

	}
	
	
	@Test
	public void test_deleteRating() {
		
		String actual = tradeController.deleteTrade(1, model);
		
		assertTrue(actual.equals("redirect:/trade/list"));

		
	}

	

}
