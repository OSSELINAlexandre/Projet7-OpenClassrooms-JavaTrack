package com.nnk.springboot.testcontroller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.BidListServices;
import com.nnk.springboot.services.CurvePointServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurveControllerTest {
	
	
	
	@MockBean
	CurvePointServices curvePointServices;
	
	@MockBean
	Model model;
	
	@MockBean
	BindingResult bidingResult;
	
	private CurveController curveController;
	
	
	
	@Before()
	public void init() {
		curveController = new CurveController();
		curveController.setCurvePointServices(curvePointServices);

	}
	
	
	@Test
	public void test_Home() {
		
		when(curvePointServices.findAllCurvePoints()).thenReturn(new ArrayList<CurvePoint>());
		
		String actual = curveController.home(model);
		
		assertTrue(actual.equals("curvePoint/list"));
	}
	
	@Test
	public void test_addBidForm() {
		
		String actual = curveController.addCurveForm(new CurvePoint());		
		assertTrue(actual.equals("curvePoint/add"));

	}
	
	@Test
	public void test_validate_ShouldReturnBidList() {
		
		
		when(bidingResult.hasErrors()).thenReturn(false);
		
		String actual = curveController.validate(new CurvePoint(), bidingResult, model);
		
		assertTrue(actual.equals("redirect:/curvePoint/list"));
		
	}

	
	@Test
	public void test_validateWithErrorShouldReturnAddPage() {
		
		
		when(bidingResult.hasErrors()).thenReturn(true);
		
		String actual = curveController.validate(new CurvePoint(), bidingResult, model);
		
		assertTrue(actual.equals("redirect:/curvePoint/add"));
		
	}
	
	@Test
	public void test_showUpdateForm_ShouldReturnUpdate() {
		
		when(curvePointServices.findSpecificCurvePoint(2)).thenReturn(new CurvePoint());
		
		String actual = curveController.showUpdateForm(2 , model);

		assertTrue(actual.equals("curvePoint/update"));

		
	}
	
	@Test
	public void test_updateBid() {
		
		 
		CurvePoint testItem = new CurvePoint();
		
		when(curvePointServices.updateAGivenCurvePoint(testItem)).thenReturn(true);

		
		String actual = curveController.updateCurve(1, testItem, bidingResult, model);
		
		
		assertTrue(actual.equals("redirect:/curvePoint/list"));

	}
	
	
	@Test
	public void test_deleteBid() {
		
		String actual = curveController.deleteCurve(1, model);
		
		assertTrue(actual.equals("redirect:/curvePoint/list"));

		
	}


}
