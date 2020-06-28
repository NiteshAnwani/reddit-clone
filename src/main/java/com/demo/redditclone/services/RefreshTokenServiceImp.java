package com.demo.redditclone.services;

import java.time.Instant;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.redditclone.exceptions.RefreshTokenNotFoundException;
import com.demo.redditclone.exceptions.SpringRedditException;
import com.demo.redditclone.models.RefreshToken;
import com.demo.redditclone.repository.RefreshTokenRepository;

@Transactional
@Service
public class RefreshTokenServiceImp implements RefreshTokenService {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Override
	public RefreshToken generateToken(String token) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setCreatedDate(Instant.now());
		return refreshTokenRepository.save(refreshToken);
	}

	@Override
	public void validateRefreshToken(String token) {
		refreshTokenRepository.findByToken(token)
				.orElseThrow(() -> new RefreshTokenNotFoundException("Invalid refresh Token"));
		refreshTokenRepository.deleteByToken(token);
	}

	@Override
	public void deleteRefreshToken(String token) {
		refreshTokenRepository.deleteByToken(token);
	}

}
