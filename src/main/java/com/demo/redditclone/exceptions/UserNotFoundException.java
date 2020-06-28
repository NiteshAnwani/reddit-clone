package com.demo.redditclone.exceptions;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String M) {
		super(M);
	}

}
