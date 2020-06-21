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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.redditclone.dto.CommentDto;
import com.demo.redditclone.services.CommentService;

@RestController
@RequestMapping(path = "/api/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping(path = "/createcomment")
	public void createComment(@RequestBody CommentDto comment) {
		commentService.createComment(comment);
	}

	@GetMapping(path = "/getbypost")
	public ResponseEntity<List<CommentDto>> getCommentByPost(@RequestParam Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentByPost(id));
	}

	@GetMapping(path = "/getbyuser")
	public ResponseEntity<List<CommentDto>> getCommentByUser(@RequestParam String name) {
		return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentByUser(name));
	}
}
