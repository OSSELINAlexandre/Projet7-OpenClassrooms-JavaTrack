package com.nnk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <b>Application est la classe 'principale' qui permet de lançer l'application
 * spring-boot-skeleton.</b>
 * <p>
 * Ce projet 7 s'inscrit dans le parcours développeur d'application Java.
 * L'objectif est de compléter un back-end à partir d'une structure existante.
 * <ul>
 * <li>Implémenter les méthodes CRUD pour chacun des domaines existants.</li>
 * <li>Créer une authentification par session (Spring Security )et par token
 * (OAuth avec Git).</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Poseidon est un logiciel d’entreprise déployé sur le Web qui vise à générer
 * davantage de transactions pour les investisseurs institutionnels qui achètent
 * et vendent des titres à revenu fixe.
 * </p>
 * 
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
