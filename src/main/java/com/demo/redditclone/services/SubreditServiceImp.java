package com.demo.redditclone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.redditclone.dto.SubredditDto;
import com.demo.redditclone.exceptions.SubredditNotFoundException;
import com.demo.redditclone.models.Subreddit;
import com.demo.redditclone.repository.SubredditRepository;

import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;

@Service
public class SubreditServiceImp implements SubredditService {

	@Autowired
	private AuthService authService;

	@Autowired
	private SubredditRepository subredditRepository;

	@Override
	public List<SubredditDto> getAll() {
		return subredditRepository.findAll().stream().map(this::maptoDto).collect(toList());
	}

	@Override
	public SubredditDto getSubreddit(Long id) {
		Subreddit subreddit = subredditRepository.findById(id).orElseThrow(() -> new SubredditNotFoundException("Subreddit not found with id "+id));
		return maptoDto(subreddit);
	}

	private SubredditDto maptoDto(Subreddit subreddit) {
		return new SubredditDto(subreddit.getId(), subreddit.getName(), subreddit.getDescription(),
				subreddit.getPosts().size());
	}

	private Subreddit mapToSubreddit(SubredditDto subredditDto) {
		return new Subreddit("/r/"+subredditDto.getName(),subredditDto.getDescription(),authService.getCurrentuser(),now());
	}

	@Override
	public SubredditDto save(SubredditDto subredditDto) {
		Subreddit subreditt = mapToSubreddit(subredditDto);
		subredditRepository.save(subreditt);
		return subredditDto;
	}
}
