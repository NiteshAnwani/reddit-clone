package com.demo.redditclone.services;

import com.demo.redditclone.models.NotificationEmail;

public interface MailService {
	public void sendMail(NotificationEmail notificationEmail);
}
