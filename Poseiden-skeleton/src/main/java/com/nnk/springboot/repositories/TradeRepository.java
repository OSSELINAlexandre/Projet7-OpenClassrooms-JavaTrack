package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface TradeRepository permet l'accès aux données pour les données de
 * type Trade.
 * 
 * 
 * @author Alexandre Osselin
 *
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
