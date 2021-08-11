package com.nnk.springboot.testcontroller;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.controllers.LoginController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTests {

	@Autowired
	private MockMvc mock;

	private LoginController loginController;

	Principal principal;

	@Before()
	public void init() {

		loginController = new LoginController();
	}

	@Test
	public void gettingLoginPage_ShouldReturnDefaultMessage() throws Exception {

		mock.perform(get("/login")).andDo(print()).andExpect(status().isOk());

	}

	@Test
	public void userLoginTest_ShouldReturnAuthenticated_WhenUserExists() throws Exception {

		mock.perform(formLogin("/login").user("Obama").password("test")).andExpect(authenticated());
	}

	@Test
	public void userLoginTest_ShouldFail_WhenUserDoesNotExists() throws Exception {

		mock.perform(formLogin("/login").user("Lincoln").password("falsepasswordodesntexists"))
				.andExpect(unauthenticated());
	}

	@Test
	public void test_getUsernamePasswordLoginInfo_ShouldReturnNAifNotAuthenticated() {

		UserDetails user = org.springframework.security.core.userdetails.User.withUsername("Alex").password("pass")
				.authorities("user").build();

		principal = new UsernamePasswordAuthenticationToken(user, null);

		StringBuffer actual = loginController.getUsernamePasswordLoginInfo(principal);

		assertTrue(actual.toString().equals("NA"));

	}

	@Test
	public void test_getUsernamePasswordLoginInfo_ShouldReturnNameisAuthenticated() {

		UserDetails user = org.springframework.security.core.userdetails.User.withUsername("Alex").password("pass")
				.authorities("USER").build();

		ArrayList<GrantedAuthority> itemList = new ArrayList<>();
		itemList.add((new SimpleGrantedAuthority("ADMIN")));

		UsernamePasswordAuthenticationToken item = new UsernamePasswordAuthenticationToken(user, null, itemList);

		principal = item;
		StringBuffer actual = loginController.getUsernamePasswordLoginInfo(principal);
		assertTrue(actual.toString().equals("Alex"));

	}

	@Test
	public void test_getOauth2LoginInfo_ShouldReturnNameisAuthenticated() {

		Set<GrantedAuthority> itemList = new HashSet<>();
		itemList.add((new SimpleGrantedAuthority("ADMIN")));

		Map<String, Object> loginTest = new HashMap<>();
		loginTest.put("login", "OSSELINAlexandre");

		OAuth2User fortheTest = new DefaultOAuth2User(itemList, loginTest, "login");

		OAuth2AuthenticationToken itemTesting = new OAuth2AuthenticationToken(fortheTest, itemList, "test");

		principal = itemTesting;

		StringBuffer actual = loginController.getOauth2LoginInfo(principal);

		assertTrue(actual.toString().equals("OSSELINAlexandre"));
	}

	@Test
	public void test_getOauth2LoginInfo_ShouldReturnNAifNotAuthenticated() {

		Set<GrantedAuthority> itemList = new HashSet<>();
		itemList.add((new SimpleGrantedAuthority("ADMIN")));

		Map<String, Object> loginTest = new HashMap<>();
		loginTest.put("login", "OSSELINAlexandre");

		OAuth2User fortheTest = new DefaultOAuth2User(itemList, loginTest, "login");

		OAuth2AuthenticationToken itemTesting = new OAuth2AuthenticationToken(fortheTest, itemList, "test");
		itemTesting.setAuthenticated(false);

		principal = itemTesting;

		StringBuffer actual = loginController.getOauth2LoginInfo(principal);

		assertTrue(actual.toString().equals("NA"));
	}

}
