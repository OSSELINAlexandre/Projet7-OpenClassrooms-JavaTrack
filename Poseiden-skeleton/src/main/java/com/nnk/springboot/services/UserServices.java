package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

/**
 * <b>UserServices est le service permettant d'insérer la logique business dans
 * le domaine métier User.</b>
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */
@Service
public class UserServices implements UserDetailsService {

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(UserServices.class);

	@Autowired
	private UserRepository userRepository;

	/**
	 * 
	 * <b>findAllUsers retourne l'ensemble des User existant dans la base de
	 * donnée.</b>
	 * 
	 * @return List<User>
	 */
	public List<User> findAllUsers() {

		return userRepository.findAll();
	}

	/**
	 * 
	 * <b>saveANewUser permet de sauvegarder un User déjà validé au préalable dans
	 * la base de donnée. </b>
	 * 
	 * @param user
	 * @return User
	 */
	public User saveANewUser(@Valid User user) {

		return userRepository.save(user);

	}

	/**
	 * <b>deleteAGivenUser permet de supprimer un User en fonction d'un ID fournit
	 * par le formulaire.</b>
	 * 
	 * 
	 * @param id
	 * @return Boolean
	 */
	public Boolean deleteAGivenUser(Integer id) {

		Optional<User> foundItem = userRepository.findById(id);

		if (foundItem.isEmpty()) {

			return false;
		} else {

			userRepository.deleteById(id);
			return true;
		}

	}

	/**
	 * <b>updateAGivenUser permet de mettre à jour un User en fonction d'un User
	 * fournit par le formulaire.</b>
	 * 
	 * 
	 * @param id
	 * @return Boolean
	 */
	public Boolean updateAGivenUser(@Valid User user) {

		Optional<User> foundItem = userRepository.findById(user.getId());

		if (foundItem.isEmpty()) {

			return false;

		} else {

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			userRepository.save(user);
			return true;

		}

	}

	/**
	 * <b>findSpecificUser permet de récupérer un User spécifique en fonction d'un
	 * ID.</b>
	 * 
	 * 
	 * @param id
	 * @return Trade
	 */
	public User findSpecificUser(Integer id) {

		return userRepository.findById(id).get();
	}

	/**
	 * <b>loadUserByUsername qui prend en argument un username vérifie si
	 * l'utilisateur existe dans la base de donnée.</b>
	 * <p>
	 * Si l'utilisateur n'existe pas dans la base de donnée, alors une
	 * UsernameNotFoundException est renvoyé
	 * </p>
	 * 
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User triesToAuthenticate = userRepository.findByUsername(username);

		if (triesToAuthenticate == null) {

			throw new UsernameNotFoundException(username);

		}

		UserDetails user = org.springframework.security.core.userdetails.User
				.withUsername(triesToAuthenticate.getUsername()).password(triesToAuthenticate.getPassword())
				.authorities(triesToAuthenticate.getRole()).build();

		return user;

	}

	//
	//
	// Setters present solely for testing purposes, can be deleted afterwards.
	//
	//
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
