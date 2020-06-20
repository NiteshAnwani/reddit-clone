package com.demo.redditclone.util;

public class Constants {
	
	public static String ACTIVATION_EMAIL;

	public Constants(String domain,String token) {
		ACTIVATION_EMAIL = "http://"+domain+"/api/auth/accountVerification/"+token;
	}
}
