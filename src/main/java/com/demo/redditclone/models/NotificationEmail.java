package com.demo.redditclone.models;

public class NotificationEmail {
	private String subject;
	private String body;
	private String recipient;
	
	

	public NotificationEmail(String subject, String body, String recipient) {
		super();
		this.subject = subject;
		this.body = body;
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

}
