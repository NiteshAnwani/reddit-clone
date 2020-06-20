package com.demo.redditclone.services;

import org.springframework.security.core.Authentication;

public interface JWTProviderService {
	public String generateToken(Authentication authenticate);

	boolean validateToken(String jwt);

	String getUserNameFromJWT(String token);
	
}
