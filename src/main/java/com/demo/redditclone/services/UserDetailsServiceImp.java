package com.demo.redditclone.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.internal.util.collections.SingletonIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.redditclone.exceptions.SpringRedditException;
import com.demo.redditclone.models.User;
import com.demo.redditclone.repository.UserRepository;

import static java.util.Collections.singletonList;

import java.util.ArrayList; 

@Service
public class UserDetailsServiceImp implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByUserName(username);
		User user = userOptional.orElseThrow(() -> new SpringRedditException(username+" User Not Found while login"));
		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),getAuthories("USER"));
	}
	
	Collection<? extends GrantedAuthority> getAuthories(String role)
	{
		return singletonList(new SimpleGrantedAuthority("USER"));
		
	}
}
