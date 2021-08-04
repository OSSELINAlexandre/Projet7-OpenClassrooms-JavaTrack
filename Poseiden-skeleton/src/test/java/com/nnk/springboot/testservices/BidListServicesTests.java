package com.nnk.springboot.testservices;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.configuration.injection.MockInjection;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListServices;

//@RunWith(MockitoJUnitRunner.class) Quand tu fais ça; c'est finalement avec Mockito qe tu run, le spring context n'existe plus; 

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidListServicesTests {
	
	@MockBean
	BidListRepository bidListRepository; 


	private BidListServices bidListServices;
	
	private BidList testingItem;
	
	@Before()
	public void init() {
		
		bidListServices = new BidListServices();
		bidListServices.setBidListRepository(bidListRepository);

		
	}
	
	
	@Test
	public void test_updateAGivenBid_ShouldReturnTrueIfExist() {
		
		
		Optional<BidList> itemOp = Optional.ofNullable(new BidList());
		
		
		BidList testItem = new BidList();
		testItem.setBidlistid(5);
		
		when(bidListRepository.findById(5)).thenReturn(itemOp);
		
		
		Boolean actual = bidListServices.updateAGivenBid(testItem);
		
		assertTrue(actual == true);
		
	}
	
	@Test
	public void test_updateAGivenBid_ShouldReturnFalseIfDoNotExist() {
		

		
		
		Optional<BidList> itemOp = Optional.empty();
				
		when(bidListRepository.findById(5)).thenReturn(itemOp);

		
		BidList testItem = new BidList();
		testItem.setBidlistid(5);
				
		Boolean actual = bidListServices.updateAGivenBid(testItem);
		
		assertTrue(actual == false);
		
	}
	
	
	@Test
	public void test_deleteAGivenBid_ShouldReturnTrueIfExist() {
		
		
		Optional<BidList> itemOp = Optional.ofNullable(new BidList());
		

		
		when(bidListRepository.findById(5)).thenReturn(itemOp);
		
		
		Boolean actual = bidListServices.deleteAGivenBid(5);
		
		assertTrue(actual == true);
		
	}
	
	@Test
	public void test_deleteAGivenBid_ShouldReturnFalseIfDoNotExist() {
		
		
		
		Optional<BidList> itemOp = Optional.empty();
				
		when(bidListRepository.findById(5)).thenReturn(itemOp);

				
		Boolean actual = bidListServices.deleteAGivenBid(5);
		
		assertTrue(actual == false);
		
	}

}
