package com.demo.redditclone.util;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

public class Constants {

	public static String ACTIVATION_EMAIL;

	public Constants(String domain, String token) {
		ACTIVATION_EMAIL = "http://" + domain + "/api/auth/accountVerification/" + token;
	}

}
