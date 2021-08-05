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

@Service
public class UserServices implements UserDetailsService {

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(UserServices.class);

	
	@Autowired
	UserRepository userRepository;

	public List<User> findAllUsers() {

		return userRepository.findAll();
	}

	public User saveANewUser(@Valid User user) {

		return userRepository.save(user);

	}

	public Boolean deleteAGivenUser(Integer id) {

		Optional<User> foundItem = userRepository.findById(id);

		if (foundItem.isEmpty()) {

			return false;
		} else {

			userRepository.deleteById(id);
			return true;
		}

	}

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

	public User findSpecificUser(Integer id) {

		return userRepository.findById(id).get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		User triesToAuthenticate = userRepository.findByUsername(username);

		if (triesToAuthenticate == null) {

			throw new UsernameNotFoundException(username);

		}

		logger.info("/////////////////// IN USER SERVICES " + triesToAuthenticate.getPassword() + "/////" + triesToAuthenticate.getRole() + "///" + triesToAuthenticate.getUsername());
		UserDetails user =  org.springframework.security.core.userdetails.User.withUsername(triesToAuthenticate.getUsername()).password(triesToAuthenticate.getPassword())
				.authorities(triesToAuthenticate.getRole()).build();

		return user;
		
	}



	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	
	
	
}
