package com.demo.redditclone.models;

import java.time.Instant;

public class BlackListToken {

	private String token;

	private Instant Date;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Instant getDate() {
		return Date;
	}

	public void setDate(Instant date) {
		Date = date;
	}

}
