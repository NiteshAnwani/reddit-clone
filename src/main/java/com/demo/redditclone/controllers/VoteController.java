package com.demo.redditclone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.redditclone.dto.VoteDto;
import com.demo.redditclone.services.VoteService;

@RestController
@RequestMapping(path = "/api/vote")
public class VoteController {

	@Autowired
	private VoteService voteService;

	@RequestMapping(path = "/dovote")
	public void doVote(@RequestBody VoteDto votedto) {
		voteService.vote(votedto);

	}
}
