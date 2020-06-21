package com.demo.redditclone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.redditclone.models.Comment;
import com.demo.redditclone.models.Post;
import com.demo.redditclone.models.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByPost(Post post);

	List<Comment> findByUser(User user);
}
