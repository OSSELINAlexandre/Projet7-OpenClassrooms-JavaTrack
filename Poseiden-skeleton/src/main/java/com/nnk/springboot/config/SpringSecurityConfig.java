package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.nnk.springboot.services.UserServices;


/**
 * <b>SpringSecurityConfig est la classe représentant la configuration de sécurité pour l'authentification et l'autorisation par session.</b>
 * <p>
 * SpringSecurityConfig est caractérisé par les élements suivants.
 * <ul>
 * <li>Une instance injectée de UserServices</li>
 * <li>Un 'Bean' de BCryptPasswordEncoder</li>
 * <li>Un 'Bean' de DaoAuthenticationProvider</li>
 * <li>L'extension de WebSecurityConfigurerAdapter</li>
 * </ul>
 * </p>
 * 
 * @see UserServices
 * @see BCryptPasswordEncoder
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private UserServices userServices;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
	
	

    /**
     * <b>Le Bean 'daoAuthenticationProvider' permet de configurer l'authentification manuellement.</b>
     * <ul>
     * <li>Le service en charge de la récupération des informations est userServices</li>
     * <li>Le cryptage du mot de passe se fait avec BCryptPasswordEncoder</li>
     * </ul>
     * 
     * <p>La classe retournée permet aurpès de Spring Security de valider ou non l'authentification de l'utilisateur tentant de se connecter à nos services. </p>
     * 
     * @return DaoAuthenticationProvider
     */
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userServices);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	
    /**
     * <b>La méthode 'configure' ayant pour argument 'AuthenticationManagerBuilder' gère l'authentification</b>
     * <p>L'authentification passe par le Bean 'daoAuthenticationProvider'</p>
     * 
     * @see daoAuthenticationProvider
     */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(daoAuthenticationProvider());
	}

	
    /**
     * <b>La méthode 'configure' ayant pour argument 'HttpSecurity' gère les authorisations des requpetes entrantes.</b>
     * <ul>
     * <li>Nous autorisons tous les fichiers du répertoires CSS à l'accès ainsi que le logo sans autorisation (permettant la mise en forme)</li>
     * <li>Toutes les pages ayant pour URL '/User' ne sont autorisées qu'aux personnes ayant pour autorité 'ADMIN'</li>
     * <li>Toutes les autres requêtes doivent être authentifiées à partir de la page login disponible en '/login'.</li>
     * 
     */
	@Override
	protected void configure(HttpSecurity http) throws Exception{

		http.
		authorizeRequests()
		.antMatchers("/css/**", "/jpeg/logo.png").permitAll()
		.antMatchers("/user/**").hasAuthority("ADMIN")
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password")
		.defaultSuccessUrl("/bidList/list", true).permitAll().and().logout().permitAll()
		.and().oauth2Login().loginPage("/login");

	}	

}
