package com.demo.redditclone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.redditclone.dto.SubredditDto;
import com.demo.redditclone.models.Subreddit;

public interface SubredditService {
	
	public List<SubredditDto> getAll();
	public SubredditDto save(SubredditDto subredditDto);
	public SubredditDto getSubreddit(Long id);	
}
