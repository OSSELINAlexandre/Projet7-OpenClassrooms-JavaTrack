package com.nnk.springboot.testservices;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointServicesTests {


	
	@MockBean
	CurvePointRepository curvePointRepository;


	
	private ArgumentCaptor<CurvePoint> curvePointCaptor = ArgumentCaptor.forClass(CurvePoint.class);

	
	private CurvePointServices curvePointServices;
	

	
	@Before()
	public void init() {
		
		curvePointServices = new CurvePointServices();
		curvePointServices.setCurvePointRepository(curvePointRepository);

		
	}
	
	
	@Test
	public void test_updateAGivenCurvePoint_ShouldReturnTrueIfExist() {
		
		
		Optional<CurvePoint> itemOp = Optional.ofNullable(new CurvePoint());
		
		
		CurvePoint testItem = new CurvePoint();
		testItem.setId(5);
		
		when(curvePointRepository.findById(5)).thenReturn(itemOp);
		
		
		Boolean actual = curvePointServices.updateAGivenCurvePoint(testItem);
		
		
		verify(curvePointRepository, times(1)).save(curvePointCaptor.capture());
		CurvePoint actualItem = curvePointCaptor.getValue();
		
		
		assertTrue(actual == true && actualItem.getId() == 5);
		
	}
	
	@Test
	public void test_updateAGivenCurvePoint_ShouldReturnFalseIfDoNotExist() {
		

		
		
		Optional<CurvePoint> itemOp = Optional.empty();
				
		when(curvePointRepository.findById(5)).thenReturn(itemOp);

		
		CurvePoint testItem = new CurvePoint();
		testItem.setId(5);
				
		Boolean actual = curvePointServices.updateAGivenCurvePoint(testItem);
		
		
		verify(curvePointRepository, times(0)).save(curvePointCaptor.capture());

		
		assertTrue(actual == false);
		
	}
	
	
	@Test
	public void test_deleteAGivenCurvePoint_ShouldReturnTrueIfExist() {
		
		
		Optional<CurvePoint> itemOp = Optional.ofNullable(new CurvePoint());
		
		
		CurvePoint testItem = new CurvePoint();
		testItem.setId(5);
		
		when(curvePointRepository.findById(5)).thenReturn(itemOp);
		
		
		Boolean actual = curvePointServices.deleteAGivenCurvePoint(5);
		
		
		verify(curvePointRepository, times(1)).findById(5);
		verify(curvePointRepository, times(1)).deleteById(5);
		
		assertTrue(actual == true);
		
	}
	
	@Test
	public void test_deleteAGivenCurvePoint_ShouldReturnFalseIfDoNotExist() {
		
		

		
		Optional<CurvePoint> itemOp = Optional.empty();
				
		when(curvePointRepository.findById(5)).thenReturn(itemOp);

		
		CurvePoint testItem = new CurvePoint();
		testItem.setId(5);
				
		Boolean actual = curvePointServices.deleteAGivenCurvePoint(5);
		
		verify(curvePointRepository, times(1)).findById(5);
		verify(curvePointRepository, times(0)).deleteById(5);
		
		assertTrue(actual == false);
		
	}

	
}
