package com.demo.redditclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.demo.redditclone.configuration.SwaggerConfiguration;

@SpringBootApplication
@Import(SwaggerConfiguration.class)
@EnableScheduling
public class RedditCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditCloneApplication.class, args);
	}

}
