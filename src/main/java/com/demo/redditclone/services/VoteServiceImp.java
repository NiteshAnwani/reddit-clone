package com.demo.redditclone.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.redditclone.dto.VoteDto;
import com.demo.redditclone.exceptions.SpringRedditException;
import com.demo.redditclone.models.Post;
import com.demo.redditclone.models.Vote;
import com.demo.redditclone.models.VoteType;
import com.demo.redditclone.repository.PostRepository;
import com.demo.redditclone.repository.VoteRepository;

@Service
public class VoteServiceImp implements VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private AuthService authService;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void vote(VoteDto votedto) {
		Post post = postRepository.findById(votedto.getPostId()).orElseThrow(
				() -> new SpringRedditException("Failed to find the post with the id = " + votedto.getPostId()));
		Optional<Vote> vote = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentuser());
		if (vote.isPresent() && vote.get().getVoteType().equals(votedto.getVoteType())) {
			throw new SpringRedditException("You have already voted " + votedto.getVoteType() + "'d for this post");
		} else {
			if (VoteType.UPVOTE.equals(votedto.getVoteType())) {
				post.setVoteCount(post.getVoteCount() + 1);
			} else {
				post.setVoteCount(post.getVoteCount() - 1);
			}
		}
		voteRepository.save(this.maptoVote(votedto));
		postRepository.save(post);
	}

	private VoteDto maptoDto(Vote vote) {
		VoteDto votedto = new VoteDto();
		votedto.setPostId(vote.getPost().getPostId());
		votedto.setVoteType(vote.getVoteType());
		return votedto;
	}

	private Vote maptoVote(VoteDto dto) {
		Vote vote = new Vote();
		vote.setPost(postRepository.findById(dto.getPostId())
				.orElseThrow(() -> new SpringRedditException("Failed to find the Post with id " + dto.getPostId())));
		vote.setUser(authService.getCurrentuser());
		vote.setVoteType(dto.getVoteType());
		return vote;
	}

}
