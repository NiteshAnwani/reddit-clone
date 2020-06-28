package com.demo.redditclone.exceptions;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtException;

public class JWTTokenException extends RuntimeException {

	public JWTTokenException(String message) {
		super(message);
	}

}
