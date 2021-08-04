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
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.BidListServices;
import com.nnk.springboot.services.TradeServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeServicesTests {
	
	
	@MockBean
	TradeRepository tradeRepository; 


	private TradeServices tradeServices;
	

	
	@Before()
	public void init() {
		
		tradeServices = new TradeServices();
		tradeServices.setTradeRepository(tradeRepository);

	}
	
	
	@Test
	public void test_updateAGivenTrade_ShouldReturnTrueIfExist() {
		
		
		Optional<Trade> itemOp = Optional.ofNullable(new Trade());
		
		
		Trade testItem = new Trade();
		testItem.setTradeId(5);
		
		when(tradeRepository.findById(5)).thenReturn(itemOp);
		
		
		Boolean actual = tradeServices.updateAGivenTrade(testItem);
		
		assertTrue(actual == true);
		
	}
	
	@Test
	public void test_updateAGivenTrade_ShouldReturnFalseIfDoNotExist() {
		

		
		
		Optional<Trade> itemOp = Optional.empty();
				
		when(tradeRepository.findById(5)).thenReturn(itemOp);

		
		Trade testItem = new Trade();
		testItem.setTradeId(5);
				
		Boolean actual = tradeServices.updateAGivenTrade(testItem);
		
		assertTrue(actual == false);
		
	}
	
	
	@Test
	public void test_deleteAGivenTrade_ShouldReturnTrueIfExist() {
		
		
		Optional<Trade> itemOp = Optional.ofNullable(new Trade());
		

		when(tradeRepository.findById(5)).thenReturn(itemOp);
		
		Boolean actual = tradeServices.deleteAGivenTrade(5);
		
		assertTrue(actual == true);
		
	}
	
	@Test
	public void test_deleteAGivenTrade_ShouldReturnFalseIfDoNotExist() {
		

		Optional<Trade> itemOp = Optional.empty();
				
		when(tradeRepository.findById(5)).thenReturn(itemOp);

				
		Boolean actual = tradeServices.deleteAGivenTrade(5);
		
		assertTrue(actual == false);
		
	}
}
