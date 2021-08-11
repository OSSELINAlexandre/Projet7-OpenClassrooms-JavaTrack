package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Interface BidListRepository permet l'accès aux données pour les données de type BidList.
 * 
 * 
 * @author Alexandre Osselin
 *
 */
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
