package com.demo.redditclone.dto;

import com.demo.redditclone.models.VoteType;

public class VoteDto {

	private VoteType voteType;
	private Long postId;

	public VoteType getVoteType() {
		return voteType;
	}

	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

}
