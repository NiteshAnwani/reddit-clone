package com.demo.redditclone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.redditclone.models.Post;
import com.demo.redditclone.models.User;
import com.demo.redditclone.models.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
	Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post,User user);
}
