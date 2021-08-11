package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface CurvePointRepository permet l'accès aux données pour les données de
 * type CurvePoint.
 * 
 * 
 * @author Alexandre Osselin
 *
 */
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
