package com.demo.redditclone.services;

import com.demo.redditclone.models.RefreshToken;

public interface RefreshTokenService {
	public RefreshToken generateToken(String token);
	public void deleteRefreshToken(String token);
	void validateRefreshToken(String token);
}
