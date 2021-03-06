package com.demo.redditclone.dto;

public class SubredditDto {

	private Long id;

	private String name;

	private String description;

	private Integer postCount;

	public SubredditDto(Long id, String name, String description, Integer postCnt) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.postCount = postCnt;
	}

	public SubredditDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPostCount() {
		return postCount;
	}

	public void setPostCount(Integer postCount) {
		this.postCount = postCount;
	}

}
