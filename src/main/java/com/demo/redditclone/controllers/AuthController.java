package com.demo.redditclone.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.redditclone.dto.AuthenticationResponse;
import com.demo.redditclone.dto.GenericResponse;
import com.demo.redditclone.dto.LoginRequest;
import com.demo.redditclone.dto.RefreshTokenRequest;
import com.demo.redditclone.dto.RegisterRequest;
import com.demo.redditclone.services.AuthService;
import com.demo.redditclone.services.RefreshTokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthService authServiceImp;

	@Autowired
	RefreshTokenService refreshTokenService;

	@PostMapping(path = "/signup")
	public GenericResponse signup(@RequestBody RegisterRequest registerRequest,
			@RequestHeader(name = "Host") String domain) {
		authServiceImp.signUp(registerRequest, domain);
		GenericResponse response = new GenericResponse();
		response.setMessage(registerRequest.getUsername() + " User Created");
		return response;
	}

	@GetMapping(path = "/accountVerification/{token}")
	public GenericResponse verifyAccount(@PathVariable String token) {
		GenericResponse response = new GenericResponse();
		response.setMessage("Account Activated SuccessFully");
		authServiceImp.verifyUser(token);
		return response;
	}

	@PostMapping(path = "/login")
	public AuthenticationResponse logIn(@RequestBody LoginRequest loginRequest) {
		AuthenticationResponse authticatedResponse = authServiceImp.logIn(loginRequest);
		return authticatedResponse;
	}

	@PostMapping(path = "/refresh/token")
	public ResponseEntity<String> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
		refreshTokenService.generateToken(refreshTokenRequest.getRefreshToken());
		return ResponseEntity.status(HttpStatus.OK)
				.body(authServiceImp.refreshToken(refreshTokenRequest).getRefreshToken());
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
		refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
		return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!!");
	}
}
