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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeServicesTests {

	@MockBean
	TradeRepository tradeRepository;

	private ArgumentCaptor<Trade> tradeCaptor = ArgumentCaptor.forClass(Trade.class);

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

		verify(tradeRepository, times(1)).save(tradeCaptor.capture());
		Trade actualItem = tradeCaptor.getValue();

		assertTrue(actual == true && actualItem.getTradeId() == 5);

	}

	@Test
	public void test_updateAGivenTrade_ShouldReturnFalseIfDoNotExist() {

		Optional<Trade> itemOp = Optional.empty();

		when(tradeRepository.findById(5)).thenReturn(itemOp);

		Trade testItem = new Trade();
		testItem.setTradeId(5);

		Boolean actual = tradeServices.updateAGivenTrade(testItem);

		verify(tradeRepository, times(0)).save(tradeCaptor.capture());

		assertTrue(actual == false);

	}

	@Test
	public void test_deleteAGivenTrade_ShouldReturnTrueIfExist() {

		Optional<Trade> itemOp = Optional.ofNullable(new Trade());

		when(tradeRepository.findById(5)).thenReturn(itemOp);

		Boolean actual = tradeServices.deleteAGivenTrade(5);

		verify(tradeRepository, times(1)).deleteById(5);

		assertTrue(actual == true);

	}

	@Test
	public void test_deleteAGivenTrade_ShouldReturnFalseIfDoNotExist() {

		Optional<Trade> itemOp = Optional.empty();

		when(tradeRepository.findById(5)).thenReturn(itemOp);

		Boolean actual = tradeServices.deleteAGivenTrade(5);

		verify(tradeRepository, times(0)).deleteById(5);

		assertTrue(actual == false);

	}

	@Test
	public void test_setIfTradeIfASellOrABuy_SellSide() {

		Trade testItem = new Trade();
		testItem.setBuyIsTrueSellIsFalse(true);
		testItem.setQuantityFromForm(77.77);
		testItem.setPriceFromForm(77.77);

		Trade actual = tradeServices.setIfTradeIfASellOrABuy(testItem);

		assertTrue(actual.getBuyPrice() == 77.77 && actual.getBuyQuantity() == 77.77);

	}

	@Test
	public void test_setIfTradeIfASellOrABuy_BuySide() {

		Trade testItem = new Trade();
		testItem.setBuyIsTrueSellIsFalse(false);
		testItem.setQuantityFromForm(77.77);
		testItem.setPriceFromForm(77.77);

		Trade actual = tradeServices.setIfTradeIfASellOrABuy(testItem);

		assertTrue(actual.getSellPrice() == 77.77 && actual.getSellQuantity() == 77.77);

	}
}
