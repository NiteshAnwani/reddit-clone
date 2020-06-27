package com.demo.redditclone.repository;

import java.time.Instant;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.demo.redditclone.models.BlackListToken;

@Repository
public class BlackListTokenRepositoryImp implements BlackListTokenRepository {

	private ArrayList<BlackListToken> blackList = new ArrayList<BlackListToken>();

	@Override
	public void addInBlackListToken(String token, Instant expirydate) {
		BlackListToken blacklistToken = new BlackListToken();
		blacklistToken.setDate(expirydate);
		blacklistToken.setToken(token);
		blackList.add(blacklistToken);
		System.out.println("BlackList size " + blackList.size());
	}

	@Override
	public boolean inBlackList(String token) {
		BlackListToken blacklistToken = blackList.stream().filter(blacklist -> blacklist.getToken().equals(token))
				.findFirst().orElse(null);
		if (blacklistToken != null)
			return true;
		else
			return false;
	}

}
