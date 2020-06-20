package com.demo.redditclone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.redditclone.models.Subreddit;

public interface SubredditRepository extends JpaRepository<Subreddit, Long>{
	
	Optional<Subreddit> findByName(String subredditName);

}
