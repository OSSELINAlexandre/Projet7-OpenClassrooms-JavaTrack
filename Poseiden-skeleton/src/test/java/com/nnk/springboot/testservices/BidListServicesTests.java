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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListServices;

//@RunWith(MockitoJUnitRunner.class) with this runner, spring context doesn't exist anymore

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidListServicesTests {

	@MockBean
	BidListRepository bidListRepository;

	private ArgumentCaptor<BidList> bidListCaptor = ArgumentCaptor.forClass(BidList.class);

	private BidListServices bidListServices;

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

		verify(bidListRepository, times(1)).save(bidListCaptor.capture());
		BidList actualItem = bidListCaptor.getValue();

		assertTrue(actual == true && actualItem.getBidlistid() == 5);

	}

	@Test
	public void test_updateAGivenBid_ShouldReturnFalseIfDoNotExist() {

		Optional<BidList> itemOp = Optional.empty();

		when(bidListRepository.findById(5)).thenReturn(itemOp);

		BidList testItem = new BidList();
		testItem.setBidlistid(5);

		Boolean actual = bidListServices.updateAGivenBid(testItem);

		verify(bidListRepository, times(1)).findById(5);
		verify(bidListRepository, times(0)).save(bidListCaptor.capture());

		assertTrue(actual == false);

	}

	@Test
	public void test_deleteAGivenBid_ShouldReturnTrueIfExist() {

		Optional<BidList> itemOp = Optional.ofNullable(new BidList());

		when(bidListRepository.findById(5)).thenReturn(itemOp);

		Boolean actual = bidListServices.deleteAGivenBid(5);

		verify(bidListRepository, times(1)).findById(5);
		verify(bidListRepository, times(1)).deleteById(5);

		assertTrue(actual == true);

	}

	@Test
	public void test_deleteAGivenBid_ShouldReturnFalseIfDoNotExist() {

		Optional<BidList> itemOp = Optional.empty();

		when(bidListRepository.findById(5)).thenReturn(itemOp);

		Boolean actual = bidListServices.deleteAGivenBid(5);

		verify(bidListRepository, times(1)).findById(5);
		verify(bidListRepository, times(0)).deleteById(5);

		assertTrue(actual == false);

	}

}
