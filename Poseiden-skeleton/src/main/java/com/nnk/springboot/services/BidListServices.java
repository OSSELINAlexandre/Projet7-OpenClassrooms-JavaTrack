package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

import ch.qos.logback.classic.Logger;

@Service
public class BidListServices {
	
	
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(BidListServices.class);

	
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
