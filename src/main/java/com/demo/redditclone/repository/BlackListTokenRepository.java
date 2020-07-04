package com.demo.redditclone.repository;

import java.time.Instant;

public interface BlackListTokenRepository {

	public void addInBlackListToken(String token, Instant date);

	public boolean inBlackList(String token);

	public void deleteBlackistExpiredToken();
}
