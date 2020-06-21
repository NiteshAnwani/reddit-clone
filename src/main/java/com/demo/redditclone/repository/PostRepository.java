package com.demo.redditclone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.redditclone.models.Post;
import com.demo.redditclone.models.Subreddit;
import com.demo.redditclone.models.User;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findAllBySubReddit(Subreddit subreddit);

	List<Post> findByUser(User user);
}
