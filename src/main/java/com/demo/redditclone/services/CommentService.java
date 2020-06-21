package com.demo.redditclone.services;

import java.util.List;

import com.demo.redditclone.dto.CommentDto;

public interface CommentService {

	public void createComment(CommentDto comment);
	public List<CommentDto> getCommentByPost(Long id);
	public List<CommentDto> getCommentByUser(String username); 
}
