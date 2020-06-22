package com.demo.redditclone.services;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.demo.redditclone.exceptions.SpringRedditException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import static io.jsonwebtoken.Jwts.parser;

@Service
public class JWTServiceProviderImp implements JWTProviderService {

	private KeyStore keyStore;

	@Value("${jwt.expiration.time}")
	private Long jwtExpirationInMillis;

	@PostConstruct
	public void init() {
		try {
			keyStore = KeyStore.getInstance("JKS");
			InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
			keyStore.load(resourceAsStream, "secret".toCharArray());
		} catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
			throw new SpringRedditException("Exception occurred while loading keystore");
		}

	}

	@Override
	public String generateToken(Authentication authentication) {
		org.springframework.security.core.userdetails.User principal = (User) authentication.getPrincipal();
		return Jwts.builder().setSubject(principal.getUsername()).signWith(getPrivateKey())
				.setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis))).compact();
	}

	@Override
	public String generateTokenWithUserName(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(Date.from((Instant.now()))).signWith(getPrivateKey())
				.setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis))).compact();
	}

	private PrivateKey getPrivateKey() {
		try {
			return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
		} catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
			throw new SpringRedditException("Exception occured while retrieving public key from keystore");
		}
	}

	@Override
	public boolean validateToken(String jwt) {
		parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
		return true;
	}

	@Override
	public String getUserNameFromJWT(String token) {
		Claims claim = parser().setSigningKey(getPublickey()).parseClaimsJws(token).getBody();
		return claim.getSubject();
	}

	private PublicKey getPublickey() {
		try {
			return keyStore.getCertificate("springblog").getPublicKey();
		} catch (KeyStoreException e) {
			throw new SpringRedditException("Exception occured while retrieving public key from keystore");
		}
	}

	@Override
	public Long getJwtExpirationInMillis() {
		return jwtExpirationInMillis;
	}
}
