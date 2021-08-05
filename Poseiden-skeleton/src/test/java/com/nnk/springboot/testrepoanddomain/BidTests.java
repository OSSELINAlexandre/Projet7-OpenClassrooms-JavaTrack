package com.nnk.springboot.testrepoanddomain;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
public class BidTests {

	@Autowired
	private BidListRepository bidListRepository;

	@Test
	public void bidListTest() {
		BidList bid = new BidList();
		bid.setAccount("Account Test");
		bid.setAsk(77.77);
		bid.setAskQuantity(77.77);
		bid.setBenchmark("Benchmark");
		bid.setBid(77.77);
		bid.setBidQuantity(77.77);
		bid.setBook("Book");
		bid.setCommentary("Commentary");
		bid.setCreationName("Creation Name");
		bid.setDealName("Deal Name");
		bid.setDealType("Deal Type");
		bid.setRevisionName("Revision Name");
		bid.setSecurity("Security");
		bid.setSide("Side");
		bid.setSourceListId("Source List Id");
		bid.setStatus("Status");
		bid.setTrader("Trader");
		bid.setType("Type");

		// Save
		bid = bidListRepository.save(bid);
		Assert.assertNotNull(bid.getBidlistid());
		Assert.assertTrue(bid.getAccount().equals("Account Test")
				&& bid.getAsk() == 77.77 && bid.getAskQuantity() == 77.77 && bid.getBenchmark().equals("Benchmark")
				&& bid.getBid() == 77.77 && bid.getBidQuantity() == 77.77 && bid.getBook().equals("Book")
				&& bid.getCommentary().equals("Commentary") && bid.getCreationName().equals("Creation Name")
				&& bid.getDealName().equals("Deal Name") && bid.getDealType().equals("Deal Type") 
				&& bid.getRevisionName().equals("Revision Name") && bid.getSecurity().equals("Security")
				&& bid.getSide().equals("Side") && bid.getSourceListId().equals("Source List Id")
				&& bid.getStatus().equals("Status") && bid.getTrader().equals("Trader") && bid.getType().equals("Type")  
				);

		// Update
		bid.setBidQuantity(20d);
		bid = bidListRepository.save(bid);
		Assert.assertEquals(bid.getBidQuantity(), 20d, 20d);

		// Find
		List<BidList> listResult = bidListRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = bid.getBidlistid();
		bidListRepository.delete(bid);
		Optional<BidList> bidList = bidListRepository.findById(id);
		Assert.assertFalse(bidList.isPresent());
	}
}
