package com.demo.redditclone.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.redditclone.dto.PostRequest;
import com.demo.redditclone.dto.PostResponse;
import com.demo.redditclone.services.PostsService;

@RestController
@RequestMapping(path = "/api/post")
public class PostsController {

	@Autowired
	private PostsService postService;

	@PostMapping(path = "/createpost")
	public void createPost(@RequestBody PostRequest postReq) {
		postService.savePost(postReq);
	}

	@GetMapping(path = "/getallpost")
	public ResponseEntity<List<PostResponse>> getAllPost() {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPost());
	}

	@GetMapping(path = "/getpost/{id}")
	public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPostById(id));
	}

	@GetMapping(path = "/getpostbysubreddit/{id}")
	public ResponseEntity<List<PostResponse>> getPostBySubreddit(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPostBySubreddit(id));
	}

	@GetMapping(path = "/getpostbyuser/{name}")
	public ResponseEntity<List<PostResponse>> getPostByUser(@PathVariable String name) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPostByUser(name));
	}

}
