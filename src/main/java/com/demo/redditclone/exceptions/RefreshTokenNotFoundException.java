package com.demo.redditclone.exceptions;

public class RefreshTokenNotFoundException extends RuntimeException {

	public RefreshTokenNotFoundException(String message) {
		super(message);
	}

}
