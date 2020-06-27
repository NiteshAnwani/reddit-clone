package com.demo.redditclone.services;

import java.time.Instant;

import org.springframework.security.core.Authentication;

public interface JWTProviderService {
	public String generateToken(Authentication authenticate);

	boolean validateToken(String jwt);

	String getUserNameFromJWT(String token);

	Long getJwtExpirationInMillis();

	String generateTokenWithUserName(String username);

	boolean inBlackList(String token);

	void addInBlackList(String token);
	
}
