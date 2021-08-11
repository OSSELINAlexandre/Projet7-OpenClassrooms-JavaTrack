package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface RuleNameRepository permet l'accès aux données pour les données de
 * type Rating.
 * 
 * 
 * @author Alexandre Osselin
 *
 */
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}
