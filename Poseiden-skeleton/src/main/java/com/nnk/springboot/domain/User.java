package com.nnk.springboot.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <b>User est la classe représentant les utilisateurs pour l'application
 * Poseidon.</b>
 * 
 * <p>
 * Les instances de cette classe proviennent de la base de donnée indiquée dans
 * le application.properties.
 * </p>
 * 
 * <p>
 * L'ensemble des attributs de cette classe proviennent d'une demande client.
 * </p>
 * 
 * <p>
 * Les attributs considérés comme obligatoires à la validation des formulaires
 * par le client sont annotées @NotBlank. Le mot de passe possède une
 * annotation @Pattern pour s'assurer de l'entrée fournit par le client.
 * </p>
 * 
 * @see TradeController
 * 
 * @author Alexandre Osselin
 * @version 1.0
 */
@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;

	@NotBlank(message = "Username is mandatory")
	@Column(name = "username")
	private String username;

	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,64}$", message = "password must contain special character, a uppercase and a lowercase at least.")
	@Column(name = "password")
	private String password;

	@NotBlank(message = "FullName is mandatory")
	@Column(name = "fullname")
	private String fullname;

	@NotBlank(message = "Role is mandatory")
	@Column(name = "role")
	private String role;

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// TODO high probability of bugging because of the override bro :)
	@Override
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
