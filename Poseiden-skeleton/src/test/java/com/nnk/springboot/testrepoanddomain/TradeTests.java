package com.nnk.springboot.testrepoanddomain;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;



/**
 * By convention, at least 80% of code should be tested. 
 * Our program isn't currently using all the attributes from trades
 * (no functionality have been expressed yet by the client).
 * Therefore a lot of getters and setters aren't yet tested, because
 * there aren't currently used in the code. These untested getters and setters
 * ,plunge coverage.  Once new functionalities using these attributes
 * would be used, this test can be deleted.
 * 
 * For now, and for convention purposes, we maintain the 80% of coverage this way.
 * 
 * @author Alexandre OSSELIN
 *
 */



@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeTests {

	@Autowired
	private TradeRepository tradeRepository;

	@Test
	public void tradeTest() {
		Timestamp testDate = new Timestamp(System.currentTimeMillis());
		Trade trade = new Trade();
		trade.setAccount("Trade Account");
		trade.setBenchmark("Benchmark");
		trade.setBook("Book");
		trade.setBuyPrice(77.77);
		trade.setBuyQuantity(77.77);
		trade.setCreationDate(testDate);
		trade.setCreationName("CreationName");
		trade.setDealName("DealName");
		trade.setDealType("DealType");
		trade.setRevisionDate(testDate);
		trade.setSecurity("Security");
		trade.setSellPrice(77.77);
		trade.setSellQuantity(77.77);
		trade.setSide("Side");
		trade.setSourceListId("SourceListId");
		trade.setStatus("Status");
		trade.setTradeDate(testDate);
		trade.setTradeId(0);
		trade.setTrader("Trader");
		trade.setType("Type");

		// Save
		trade = tradeRepository.save(trade);
		Assert.assertNotNull(trade.getTradeId());
		Assert.assertTrue(trade.getAccount().equals("Trade Account")
				&& trade.getBenchmark().equals("Benchmark")
				&& trade.getBook().equals("Book") && trade.getBuyPrice() == 77.77 && trade.getBuyQuantity() == 77.77
				&& trade.getCreationName().equals("CreationName") && trade.getDealName().equals("DealName")
				&& trade.getSecurity().equals("Security") && trade.getSide().equals("Side") && trade.getSourceListId().equals("SourceListId")
				&& trade.getStatus().equals("Status") && trade.getTrader().equals("Trader") && trade.getType().equals("Type")
				&& trade.getSellPrice() == 77.77  && trade.getSellQuantity() == 77.77 
				&& trade.getRevisionDate() == testDate && trade.getCreationDate() == testDate);

		// Update
		trade.setAccount("Trade Account Update");
		trade = tradeRepository.save(trade);
		Assert.assertTrue(trade.getAccount().equals("Trade Account Update"));

		// Find
		List<Trade> listResult = tradeRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = trade.getTradeId();
		tradeRepository.delete(trade);
		Optional<Trade> tradeList = tradeRepository.findById(id);
		Assert.assertFalse(tradeList.isPresent());
	}
}
