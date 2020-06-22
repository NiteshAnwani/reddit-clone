package com.demo.redditclone.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.redditclone.dto.AuthenticationResponse;
import com.demo.redditclone.dto.LoginRequest;
import com.demo.redditclone.dto.RefreshTokenRequest;
import com.demo.redditclone.dto.RegisterRequest;
import com.demo.redditclone.exceptions.SpringRedditException;
import com.demo.redditclone.models.NotificationEmail;
import com.demo.redditclone.models.User;
import com.demo.redditclone.models.VerificationToken;
import com.demo.redditclone.repository.UserRepository;
import com.demo.redditclone.repository.VerificationTokenRepository;
import com.demo.redditclone.util.Constants;

@Service
public class AuthServiceImp implements AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MailService mailService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Autowired
	private JWTProviderService jwtProviderService;

	@Autowired
	private RefreshTokenService refreshTokenService;

	@Override
	@Transactional
	public void signUp(RegisterRequest registerRequest, String domain) {
		User user = new User();
		user.setUserName(registerRequest.getUsername());
		user.setPassword(new BCryptPasswordEncoder().encode(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		user.setCreated(Instant.now());
		user.setActive(false);

		userRepository.save(user);

		String token = generateVerificationToken(user);
		String mailBody = "Activate Your Account by click on below link "
				+ new Constants(domain, token).ACTIVATION_EMAIL + "";
		mailService.sendMail(new NotificationEmail("Please Activate Your Account", mailBody, user.getEmail()));

	}

	@Override
	@Transactional
	public User getCurrentuser() {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return userRepository.findByUserName(principal.getUsername())
				.orElseThrow(() -> new SpringRedditException("Current User not Found "));
	}

	@Override
	public String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		return token;
	}

	@Override
	public String verifyUser(String token) {
		Optional<VerificationToken> verTokenOptional = verificationTokenRepository.findByToken(token);
		verTokenOptional.orElseThrow(() -> new SpringRedditException("Token Not Founf"));
		return enableAndVerifyUser(verTokenOptional.get());
	}

	@Override
	public AuthenticationResponse logIn(LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String authenticationToken = jwtProviderService.generateToken(authenticate);
		return new AuthenticationResponse(authenticationToken, loginRequest.getUsername(),
				refreshTokenService.generateToken(authenticationToken).getToken(),
				Instant.now().plusMillis(jwtProviderService.getJwtExpirationInMillis()));
	}

	@Override
	public boolean isLoggedIn() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
	}

	@Override
	public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
		String token = jwtProviderService.generateTokenWithUserName(refreshTokenRequest.getUserName());
		return new AuthenticationResponse(token, refreshTokenRequest.getUserName(),
				refreshTokenRequest.getRefreshToken(),
				Instant.now().plusMillis(jwtProviderService.getJwtExpirationInMillis()));
	}

	private String enableAndVerifyUser(VerificationToken verToken) {
		String userName = verToken.getUser().getUserName();
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new SpringRedditException("User Not found for the given token"));
		user.setActive(true);
		userRepository.save(user);
		return "Account Verified SuccessFully";
	}

}
