package com.demo.redditclone.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.redditclone.dto.PostRequest;
import com.demo.redditclone.dto.PostResponse;
import com.demo.redditclone.exceptions.PostNotFoundException;
import com.demo.redditclone.exceptions.SubredditNotFoundException;
import com.demo.redditclone.exceptions.UserNotFoundException;
import com.demo.redditclone.models.Post;
import com.demo.redditclone.models.Subreddit;
import com.demo.redditclone.models.Vote;
import com.demo.redditclone.models.VoteType;
import com.demo.redditclone.repository.CommentRepository;
import com.demo.redditclone.repository.PostRepository;
import com.demo.redditclone.repository.SubredditRepository;
import com.demo.redditclone.repository.UserRepository;
import com.demo.redditclone.repository.VoteRepository;

import static java.util.stream.Collectors.toList;

@Service
public class PostsServiceImpl implements PostsService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SubredditRepository subredditRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private AuthService authService;

	@Autowired
	private VoteRepository voteRepository;

	@Override
	public void savePost(PostRequest postReq) {
		Post post = mapToPost(postReq);
		postRepository.save(post);
	}

	@Override
	public List<PostResponse> getAllPost() {
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(this::mapToDto).collect(toList());
	}

	@Override
	public PostResponse getPostById(Long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new PostNotFoundException("POST NOT FOUND WITH ID " + id));
		return mapToDto(post);
	}

	@Override
	public List<PostResponse> getPostBySubreddit(Long id) {
		List<Post> post = postRepository.findAllBySubReddit(subredditRepository.findById(id)
				.orElseThrow(() -> new PostNotFoundException("POST NOT FOUND WITH SUBREDDIT ID " + id)));
		return post.stream().map(this::mapToDto).collect(toList());
	}

	@Override
	public List<PostResponse> getPostByUser(String username) {
		List<Post> post = postRepository.findByUser(userRepository.findByUserName(username)
				.orElseThrow(() -> new UserNotFoundException("User not found with the name " + username)));
		return post.stream().map(this::mapToDto).collect(toList());
	}

	private Post mapToPost(PostRequest postReq) {
		Subreddit subreddit = subredditRepository.findByName(postReq.getSubredditName()).orElseThrow(
				() -> new SubredditNotFoundException("Subreddit Not Found with Name " + postReq.getSubredditName()));
		Post post = new Post();
		post.setPostName(postReq.getPostName());
		post.setPostDescription(postReq.getDescription());
		post.setUrl(postReq.getUrl());
		post.setCreatedDate(Instant.now());
		post.setSubReddit(subreddit);
		post.setVoteCount(0);
		post.setUser(authService.getCurrentuser());
		return post;
	}

	private PostResponse mapToDto(Post post) {
		PostResponse postRes = new PostResponse();
		postRes.setId(post.getPostId());
		postRes.setPostName(post.getPostName());
		postRes.setUrl(post.getUrl());
		postRes.setDescription(post.getPostDescription());
		postRes.setUserName(post.getUser().getUserName());
		postRes.setSubredditName(post.getSubReddit().getName());
		postRes.setCommentCount(commentRepository.findByPost(post).size());
		postRes.setDuration("Created on " + post.getCreatedDate());
		postRes.setUpVote(checkVoteType(post, VoteType.UPVOTE));
		postRes.setDownVote(checkVoteType(post, VoteType.DOWNVOTE));
		postRes.setVoteCount(
				voteRepository.findByPost(post).isPresent() ? voteRepository.findByPost(post).get().size() : 0);

		return postRes;
	}

	private boolean checkVoteType(Post post, VoteType votetype) {
		if (authService.isLoggedIn()) {
			Optional<Vote> voteforpost = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,
					authService.getCurrentuser());
			return voteforpost.filter(vote -> vote.getVoteType().equals(votetype)).isPresent();
		}
		return false;
	}

}
