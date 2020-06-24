package com.demo.redditclone.exceptions;

import net.bytebuddy.implementation.bind.annotation.Super;

public class SpringRedditException extends RuntimeException {

	public SpringRedditException(String exMessage) {
		super(exMessage);
		System.out.println("^^^^^^^^^^^" + exMessage);
	}
}
