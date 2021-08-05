package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListServices {
	
	

	
	@Autowired
	BidListRepository bidListRepository;

	public List<BidList> findAllBids() {
		
		return bidListRepository.findAll();
	}

	public BidList saveANewBid(@Valid BidList bid) {
		
		return bidListRepository.save(bid);
		
	}

	public Boolean deleteAGivenBid(Integer id) {
		
		Optional<BidList> foundItem = bidListRepository.findById(id);
		
		if(foundItem.isEmpty()) {
			
			return false;
			
		}else {
			
			bidListRepository.deleteById(id);
			return true;
			
		}
		
	}
	
	

	public Boolean updateAGivenBid(@Valid BidList bidList) {
		
		Optional<BidList> foundItem = bidListRepository.findById(bidList.getBidlistid());
		
		if(foundItem.isEmpty()) {
			
			return false;
			
		}else {
			
			bidListRepository.save(bidList);
//			logger.info("=====|||||====" + test.getBidlistid() + "'''''' ''' " + test.getBidQuantity());
			return true;
			
		}
	}

	public BidList findSpecificBid(Integer id) {
		return bidListRepository.findById(id).get();
	}
	
	//
	//
	// Setters present solely for testing purposes, can be deleted afterwards.
	//
	//
	
	

	public void setBidListRepository(BidListRepository bidListRepository) {
		this.bidListRepository = bidListRepository;
	}
	

	
	

}
