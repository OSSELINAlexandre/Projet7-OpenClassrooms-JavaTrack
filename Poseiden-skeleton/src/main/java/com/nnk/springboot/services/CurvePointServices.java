package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;


@Service
public class CurvePointServices {
	
	
	
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CurvePointServices.class);

	
	@Autowired
	CurvePointRepository curvePointRepository;

	public List<CurvePoint> findAllCurvePoints() {
		
		return curvePointRepository.findAll();
	}

	public CurvePoint saveANewCurvedPoint(@Valid CurvePoint curvePoint) {

		logger.info("=========" + curvePoint.getValue());
		return curvePointRepository.save(curvePoint);
	}

	public CurvePoint findSpecificCurvePoint(Integer id) {
		return curvePointRepository.findById(id).get();

	}
	
	
	
	
	public Boolean deleteAGivenCurvePoint(Integer id) {
		
		Optional<CurvePoint> foundItem = curvePointRepository.findById(id);
		
		if(foundItem.isEmpty()) {
			
			return false;
			
		}else {
			
			curvePointRepository.deleteById(id);
			return true;
			
		}
		
		
	}

	
	
	public Boolean updateAGivenCurvePoint(@Valid CurvePoint curvePoint) {
		
		Optional<CurvePoint> test = curvePointRepository.findById(curvePoint.getId());
		
		if(test.isEmpty()) {
			
			return false;
			
		}else {
			
			curvePointRepository.save(curvePoint);
			return true;
			
		}
	}

	public void setCurvePointRepository(CurvePointRepository curvePointRepository) {
		this.curvePointRepository = curvePointRepository;
	}
	
	
	

}
