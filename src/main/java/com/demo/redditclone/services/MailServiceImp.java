package com.demo.redditclone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.demo.redditclone.models.NotificationEmail;

@Service
public class MailServiceImp implements MailService{
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailContenBuilderService mailContentBuilderService;
	
	@Override
	public void sendMail(NotificationEmail notificationEmail) {
		MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("reddit@spring.com");
			messageHelper.setTo(notificationEmail.getRecipient());
			messageHelper.setSubject(notificationEmail.getSubject());
			messageHelper.setText(mailContentBuilderService.buildContent(notificationEmail.getBody()));
		};
		try {
			mailSender.send(mimeMessagePreparator);
			System.out.println("Mail Sent");
		} catch (Exception e) {
			System.out.println("Exception in Mail sending "+e);
		}
		
	}

}
