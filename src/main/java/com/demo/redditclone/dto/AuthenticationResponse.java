package com.demo.redditclone.dto;

import java.time.Instant;

public class AuthenticationResponse {
	private String authenticationToken;
	private String username;
	private String refreshToken;
	private Instant experiesAt;

	public AuthenticationResponse(String authenticationToken, String username, String refreshToken,
			Instant experiesAt) {
		super();
		this.authenticationToken = authenticationToken;
		this.username = username;
		this.refreshToken = refreshToken;
		this.experiesAt = experiesAt;
	}

	public Instant getExperiesAt() {
		return experiesAt;
	}

	public void setExperiesAt(Instant experiesAt) {
		this.experiesAt = experiesAt;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
