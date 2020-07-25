package com.demo.redditclone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.redditclone.dto.CommentDto;
import com.demo.redditclone.exceptions.PostNotFoundException;
import com.demo.redditclone.exceptions.SpringRedditException;
import com.demo.redditclone.exceptions.UserNotFoundException;
import com.demo.redditclone.models.Comment;
import com.demo.redditclone.repository.CommentRepository;
import com.demo.redditclone.repository.PostRepository;
import com.demo.redditclone.repository.UserRepository;

import static java.util.stream.Collectors.toList;

import java.time.Instant;

@Service
public class CommentServiceImp implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthService authService;

	@Override
	public void createComment(CommentDto commentDto) {
		commentRepository.save(maptoComment(commentDto));
	}

	@Override
	public List<CommentDto> getCommentByPost(Long id) {
		List<Comment> comment = commentRepository.findByPost(postRepository.findById(id)
				.orElseThrow(() -> new PostNotFoundException("Post not found with the id " + id)));
		return comment.stream().map(this::maptoDto).collect(toList());
	}

	@Override
	public List<CommentDto> getCommentByUser(String username) {
		List<Comment> comment = commentRepository.findByUser(userRepository.findByUserName(username)
				.orElseThrow(() -> new UserNotFoundException("User not found with the user name as " + username)));
		return comment.stream().map(this::maptoDto).collect(toList());
	}

	private Comment maptoComment(CommentDto commentDto) {
		Comment comment = new Comment();
		comment.setText(commentDto.getText());
		comment.setPost(postRepository.findById(commentDto.getPostId()).orElseThrow(
				() -> new PostNotFoundException("Failed to find the post by id as id - " + commentDto.getId())));
		/*
		 * comment.setUser(
		 * userRepository.findByUserName(commentDto.getUserName()).orElseThrow(() -> new
		 * UserNotFoundException( "failed to find the user with username as " +
		 * commentDto.getUserName())));
		 */
		comment.setUser(this.authService.getCurrentuser());
		comment.setCreateddate(Instant.now());
		return comment;
	}

	private CommentDto maptoDto(Comment comment) {
		CommentDto commentdto = new CommentDto();
		commentdto.setId(comment.getId());
		commentdto.setCreatedDate(comment.getCreateddate());
		commentdto.setPostId(comment.getPost().getPostId());
		commentdto.setText(comment.getText());
		commentdto.setUserName(comment.getUser().getUserName());
		return commentdto;
	}

}
