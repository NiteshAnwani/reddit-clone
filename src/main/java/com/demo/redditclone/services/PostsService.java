package com.demo.redditclone.services;

import java.util.List;

import com.demo.redditclone.dto.PostRequest;
import com.demo.redditclone.dto.PostResponse;

public interface PostsService {
	
	public void savePost(PostRequest postReq);
	public List<PostResponse> getAllPost();
	public PostResponse getPostById(Long id);
	public List<PostResponse> getPostBySubreddit(Long id);
	public List<PostResponse> getPostByUser(String username);

}
