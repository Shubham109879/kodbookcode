package com.kodbook.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Post post;
	
	private String content;
	
	private LocalDate createdDate; // Changed to LocalDate
    private LocalTime createdTime; // New field for LocalTime
    
    
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(Long id, User user, Post post,String content,LocalDate createdDate,LocalTime createdTime) {
		super();
		this.id = id;
		this.user = user;
		this.post = post;
		this.content=content;
		this.createdDate=createdDate;
		this.createdTime=createdTime;
	}

	

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalTime createdTime) {
		this.createdTime = createdTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", user=" + user + ", post=" + post + "]";
	}
  
  
}
