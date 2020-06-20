package com.demo.redditclone.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.redditclone.dto.SubredditDto;
import com.demo.redditclone.repository.SubredditRepository;
import com.demo.redditclone.services.SubredditService;

@RestController
@RequestMapping(path = "/api/subreddit")
public class SubredditController {

	@Autowired
	private SubredditService subreddditServices;
	
	
	@GetMapping(path = "/getallsubreddit")
	public List<SubredditDto> getAll()
	{
		return subreddditServices.getAll();
	}
	
	@PostMapping(path = "/createsubreddit")
	public SubredditDto createSubreddit(@RequestBody @Valid SubredditDto subredditDto)
	{
		return subreddditServices.save(subredditDto);
	}
	
	@GetMapping(path = "/getsubreddit/{id}")
	public SubredditDto getSubredditById(@PathVariable Long id)
	{
		return subreddditServices.getSubreddit(id);
	}
}
