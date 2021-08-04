package com.nnk.springboot.testcontroller;


import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.validation.Valid;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidListControllerTests {
	
	@MockBean
	BidListServices bidListServices;
	
	@MockBean
	Model model;
	
	@MockBean
	BindingResult bidingResult;
	
	private BidListController bidListController;
	
	
	
	@Before()
	public void init() {
		bidListController = new BidListController();
		bidListController.setBidListServices(bidListServices);

	}
	
	
	@Test
	public void test_Home() {
		
		when(bidListServices.findAllBids()).thenReturn(new ArrayList<BidList>());
		
		String actual = bidListController.home(model);
		
		assertTrue(actual.equals("bidList/list"));
	}
	
	@Test
	public void test_addBidForm() {
		
		String actual = bidListController.addBidForm(new BidList());		
		assertTrue(actual.equals("bidList/add"));

	}
	
	@Test
	public void test_validate_ShouldReturnBidList() {
		
		
		when(bidingResult.hasErrors()).thenReturn(false);
		
		String actual = bidListController.validate(new BidList(), bidingResult, model);
		
		assertTrue(actual.equals("redirect:/bidList/list"));
		
	}

	
	@Test
	public void test_validateWithErrorShouldReturnAddPage() {
		
		
		when(bidingResult.hasErrors()).thenReturn(true);
		
		String actual = bidListController.validate(new BidList(), bidingResult, model);
		
		assertTrue(actual.equals("bidList/add"));
		
	}
	
	@Test
	public void test_showUpdateForm_ShouldReturnUpdate() {
		
		when(bidListServices.findSpecificBid(2)).thenReturn(new BidList());
		
		String actual = bidListController.showUpdateForm(2 , model);

		assertTrue(actual.equals("bidList/update"));

		
	}
	
	@Test
	public void test_updateBid() {
		
		 
		BidList testItem = new BidList();
		
		when(bidListServices.updateAGivenBid(testItem)).thenReturn(true);

		
		String actual = bidListController.updateBid(1, testItem, bidingResult, model);
		
		
		assertTrue(actual.equals("redirect:/bidList/list"));

	}
	
	
	@Test
	public void test_deleteBid() {
		
		String actual = bidListController.deleteBid(1, model);
		
		assertTrue(actual.equals("redirect:/bidList/list"));

		
	}

}
