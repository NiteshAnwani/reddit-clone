package com.demo.redditclone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.redditclone.models.Post;
import com.demo.redditclone.models.Subreddit;
import com.demo.redditclone.models.User;

public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findAllBySubReddit(Subreddit subreddit);

	Optional<Post> findByUser(User user);
}
