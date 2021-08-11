package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

/**
 * <b>BidListServices est le service permettant d'insérer la logique business
 * dans le domaine métier BidList.</b>
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */
@Service
public class BidListServices {

	@Autowired
	private BidListRepository bidListRepository;

	/**
	 * 
	 * <b>findAllBids retourne l'ensemble des BidList existant dans la base de
	 * donnée.</b>
	 * 
	 * @return List<BidList>
	 */
	public List<BidList> findAllBids() {

		return bidListRepository.findAll();
	}

	/**
	 * 
	 * <b>saveANewBid permet de sauvegarder un BidList déjà validé au préalable dans
	 * la base de donnée. </b>
	 * 
	 * @param bid
	 * @return BidList
	 */
	public BidList saveANewBid(@Valid BidList bid) {

		return bidListRepository.save(bid);

	}

	/**
	 * <b>deleteAGivenBid permet de supprimer un BidList en fonction d'un ID fournit
	 * par le formulaire.</b>
	 * 
	 * 
	 * @param id
	 * @return Boolean
	 */
	public Boolean deleteAGivenBid(Integer id) {

		Optional<BidList> foundItem = bidListRepository.findById(id);

		if (foundItem.isEmpty()) {

			return false;

		} else {

			bidListRepository.deleteById(id);
			return true;

		}

	}

	/**
	 * <b>updateAGivenBid permet de mettre à jour un BidList en fonction d'un
	 * BidList fournit par le formulaire.</b>
	 * 
	 * 
	 * @param id
	 * @return Boolean
	 */
	public Boolean updateAGivenBid(@Valid BidList bidList) {

		Optional<BidList> foundItem = bidListRepository.findById(bidList.getBidlistid());

		if (foundItem.isEmpty()) {

			return false;

		} else {

			bidListRepository.save(bidList);
			return true;

		}
	}

	/**
	 * <b>findSpecificBid permet de récupérer un BidList spécifique en fonction d'un
	 * ID.</b>
	 * 
	 * 
	 * @param id
	 * @return BidList
	 */
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
