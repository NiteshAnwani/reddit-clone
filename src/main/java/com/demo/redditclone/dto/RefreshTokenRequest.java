package com.demo.redditclone.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author nitesh.anwani
 *
 */
public class RefreshTokenRequest {

	@NotBlank
	private String refreshToken;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
