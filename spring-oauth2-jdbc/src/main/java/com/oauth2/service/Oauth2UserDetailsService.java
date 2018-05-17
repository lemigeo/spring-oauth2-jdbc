package com.oauth2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oauth2.repository.AuthorityRepository;
import com.oauth2.domain.Account;
import com.oauth2.domain.Authority;
import com.oauth2.repository.AccountRepository;

@Service
public class Oauth2UserDetailsService implements UserDetailsService {
	
	@Autowired
	private AccountRepository account;
	
	@Autowired
	private AuthorityRepository auth;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username == null || username.isEmpty()) {
			throw new UsernameNotFoundException("Username is empty");
		}
		Account entity = account.findByUsername(username).get();
		if(entity != null && entity.getEnabled()) {
			UserDetails user = loadUserDetails(username);
			if(user != null) {
				return user;
			}
		}
	    throw new UsernameNotFoundException(
	        "Unauthorized client_id or username not found: " + username);
	}

	private UserDetails loadUserDetails(String username) {
		User user = null;
		List<Authority> entities = auth.findByUsername(username).get();
		if(entities != null) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			for(Authority entity : entities) {
				authorities.add(new SimpleGrantedAuthority(entity.getAuthority()));
			}
			user = new User(username, "", authorities);
		}
		return user;
	}
}
