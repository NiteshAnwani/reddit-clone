package com.demo.redditclone.exceptions;

public class VerificationTokenNotFound extends RuntimeException {

	public VerificationTokenNotFound(String message) {
		super(message);
	}
}
