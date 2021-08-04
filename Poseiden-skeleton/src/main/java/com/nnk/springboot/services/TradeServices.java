package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;


@Service
public class TradeServices {
	
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(TradeServices.class);
	
	@Autowired
	TradeRepository tradeRepository;

	public List<Trade> findAllTrades() {
		return tradeRepository.findAll();
	}
	

	public Trade saveANewTrade(@Valid Trade trade) {
		
		
		return tradeRepository.save(trade);
		
	}


	public Trade findSpecificTrade(Integer id) {
		return tradeRepository.findById(id).get();

	}

	public Boolean updateAGivenTrade(@Valid Trade trade) {
		
		Optional<Trade> foundItem = tradeRepository.findById(trade.getTradeId());
		
		if(foundItem.isEmpty()) {
			
			return false;
			
		}else {
			
			tradeRepository.save(trade);
			return true;
			
		}
	}

	public Boolean deleteAGivenTrade(Integer id) {
		
		Optional<Trade> foundItem = tradeRepository.findById(id);
		
		if(foundItem.isEmpty()) {
			
			return false;
			
		}else {
			
			tradeRepository.deleteById(id);
			return true;
			
		}
	}

	
	public Trade setIfTradeIfASellOrABuy(@Valid Trade trade) {
		
		
		if(trade.getBuyIsTrueSellIsFalse()) {
			
			trade.setBuyPrice(trade.getPriceFromForm());
			trade.setBuyQuantity(trade.getQuantityFromForm());
			logger.info("======== was in buy");

			
		}else {
			
			trade.setSellPrice(trade.getPriceFromForm());
			trade.setSellQuantity(trade.getQuantityFromForm());
			logger.info("======== was in sell");
		}
		
		return trade;
		
		
	}


	public void setTradeRepository(TradeRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}
	
	

}
