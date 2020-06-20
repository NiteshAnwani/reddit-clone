package com.demo.redditclone.models;

import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Subreddit {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotBlank(message = "Community Name can not be blank")
	private String name;

	@NotBlank(message = "Community decription can not be blank")
	private String description;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Post> posts;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	private Instant createdDate;

	
	
	public Subreddit() {
		super();
	}

	public Subreddit(String name,String description, User user,Instant date) {
		this.name = name;
		this.description = description;
		this.user = user;
		this.createdDate = date;
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

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

}
