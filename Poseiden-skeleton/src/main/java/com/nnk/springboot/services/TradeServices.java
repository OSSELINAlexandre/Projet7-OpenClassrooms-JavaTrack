package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

/**
 * <b>TradeServices est le service permettant d'insérer la logique business dans
 * le domaine métier Trade.</b>
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */
@Service
public class TradeServices {

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(TradeServices.class);

	@Autowired
	private TradeRepository tradeRepository;

	/**
	 * 
	 * <b>findAllTrades retourne l'ensemble des Trade existant dans la base de
	 * donnée.</b>
	 * 
	 * @return List<Trade>
	 */
	public List<Trade> findAllTrades() {
		return tradeRepository.findAll();
	}

	/**
	 * 
	 * <b>saveANewTrade permet de sauvegarder un Trade déjà validé au préalable dans
	 * la base de donnée. </b>
	 * 
	 * @param trade
	 * @return Trade
	 */
	public Trade saveANewTrade(@Valid Trade trade) {

		return tradeRepository.save(trade);

	}

	/**
	 * <b>findSpecificTrade permet de récupérer un Trade spécifique en fonction d'un
	 * ID.</b>
	 * 
	 * 
	 * @param id
	 * @return Trade
	 */
	public Trade findSpecificTrade(Integer id) {
		return tradeRepository.findById(id).get();

	}

	/**
	 * <b>updateAGivenTrade permet de mettre à jour un Trade en fonction d'un Trade
	 * fournit par le formulaire.</b>
	 * 
	 * 
	 * @param id
	 * @return Boolean
	 */
	public Boolean updateAGivenTrade(@Valid Trade trade) {

		Optional<Trade> foundItem = tradeRepository.findById(trade.getTradeId());

		if (foundItem.isEmpty()) {

			return false;

		} else {

			tradeRepository.save(trade);
			return true;

		}
	}

	/**
	 * <b>deleteAGivenTrade permet de supprimer un Trade en fonction d'un ID fournit
	 * par le formulaire.</b>
	 * 
	 * 
	 * @param id
	 * @return Boolean
	 */
	public Boolean deleteAGivenTrade(Integer id) {

		Optional<Trade> foundItem = tradeRepository.findById(id);

		if (foundItem.isEmpty()) {

			return false;

		} else {

			tradeRepository.deleteById(id);
			return true;

		}
	}

	/**
	 * setIfTradeIfASellOrABuy, utilisant un Trade validé, permet d'insérer les
	 * paramètres dans les champs Achats ou Vente en fonction de l'input du client.
	 * 
	 * 
	 * 
	 * @param trade
	 * @return
	 */
	public Trade setIfTradeIfASellOrABuy(@Valid Trade trade) {

		if (trade.getBuyIsTrueSellIsFalse()) {

			trade.setBuyPrice(trade.getPriceFromForm());
			trade.setBuyQuantity(trade.getQuantityFromForm());
			logger.info("======== was in buy");

		} else {

			trade.setSellPrice(trade.getPriceFromForm());
			trade.setSellQuantity(trade.getQuantityFromForm());
			logger.info("======== was in sell");
		}

		return trade;

	}

	//
	//
	// Setters present solely for testing purposes, can be deleted afterwards.
	//
	//
	public void setTradeRepository(TradeRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}

}
