package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

/**
 * <b>CurvePointServices est le service permettant d'insérer la logique business
 * dans le domaine métier CurvePoint.</b>
 * 
 * @author Alexandre Osselin
 * @version 1.0
 *
 */
@Service
public class CurvePointServices {

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CurvePointServices.class);

	@Autowired
	private CurvePointRepository curvePointRepository;

	/**
	 * 
	 * <b>findAllCurvePoints retourne l'ensemble des CurvePoint existant dans la
	 * base de donnée.</b>
	 * 
	 * @return List<CurvePoint>
	 */
	public List<CurvePoint> findAllCurvePoints() {

		return curvePointRepository.findAll();
	}

	/**
	 * 
	 * <b>saveANewCurvedPoint permet de sauvegarder un CurvePoint déjà validé au
	 * préalable dans la base de donnée. </b>
	 * 
	 * @param curvePoint
	 * @return CurvePoint
	 */
	public CurvePoint saveANewCurvedPoint(@Valid CurvePoint curvePoint) {

		logger.info("=========" + curvePoint.getValue());
		return curvePointRepository.save(curvePoint);
	}

	/**
	 * <b>findSpecificCurvePoint permet de récupérer un CurvePoint spécifique en
	 * fonction d'un ID.</b>
	 * 
	 * 
	 * @param id
	 * @return CurvePoint
	 */
	public CurvePoint findSpecificCurvePoint(Integer id) {
		return curvePointRepository.findById(id).get();

	}

	/**
	 * <b>deleteAGivenCurvePoint permet de supprimer un CurvePoint en fonction d'un
	 * ID fournit par le formulaire.</b>
	 * 
	 * 
	 * @param id
	 * @return Boolean
	 */
	public Boolean deleteAGivenCurvePoint(Integer id) {

		Optional<CurvePoint> foundItem = curvePointRepository.findById(id);

		if (foundItem.isEmpty()) {

			return false;

		} else {

			curvePointRepository.deleteById(id);
			return true;

		}

	}

	/**
	 * <b>updateAGivenCurvePoint permet de mettre à jour un CurvePoint en fonction
	 * d'un CurvePoint fournit par le formulaire.</b>
	 * 
	 * 
	 * @param id
	 * @return Boolean
	 */
	public Boolean updateAGivenCurvePoint(@Valid CurvePoint curvePoint) {

		Optional<CurvePoint> test = curvePointRepository.findById(curvePoint.getId());

		if (test.isEmpty()) {

			return false;

		} else {

			curvePointRepository.save(curvePoint);
			return true;

		}
	}

	//
	//
	// Setters present solely for testing purposes, can be deleted afterwards.
	//
	//
	public void setCurvePointRepository(CurvePointRepository curvePointRepository) {
		this.curvePointRepository = curvePointRepository;
	}

}
