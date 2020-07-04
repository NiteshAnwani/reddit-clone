package com.demo.redditclone.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.demo.redditclone.models.BlackListToken;
import com.demo.redditclone.repository.BlackListTokenRepository;

/**
 * @author nitesh.anwani
 * 
 *         Scheduled Job at every 5 minutes to remove the expired token from In
 *         Memory Lists.
 */
@Component
public class Scheduler {

	@Autowired
	BlackListTokenRepository blackListTokenRepository;

	@Scheduled(cron = "0 * * ? * *")
	public void cronJob() {
		blackListTokenRepository.deleteBlackistExpiredToken();
	}
}
