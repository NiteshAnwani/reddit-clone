package com.demo.redditclone.services;

import com.demo.redditclone.dto.AuthenticationResponse;
import com.demo.redditclone.dto.LoginRequest;
import com.demo.redditclone.dto.RefreshTokenRequest;
import com.demo.redditclone.dto.RegisterRequest;
import com.demo.redditclone.models.User;

public interface AuthService {
	public void signUp(RegisterRequest registerRequest,String domain);
	public String generateVerificationToken(User user);
	public String verifyUser(String token);
	public AuthenticationResponse logIn(LoginRequest loginRequest);
	public User getCurrentuser();
	boolean isLoggedIn();
	AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
