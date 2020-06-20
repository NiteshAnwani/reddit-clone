package com.demo.redditclone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilderServiceImp implements MailContenBuilderService{
	
	@Autowired
	private TemplateEngine templateEngine;
	
	
	@Override
	public String buildContent(String message) {
		Context context=new Context();
		context.setVariable("message", message);
		return templateEngine.process("verificationMail", context);
	}

}
