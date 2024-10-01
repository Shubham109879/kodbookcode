package com.kodbook.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "likes")
public class Like 
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
 
  @ManyToOne
  @JoinColumn(name="user_id",nullable = false)
  private User user;
 
  @ManyToOne
  @JoinColumn(name="post_id",nullable = false)
  private Post post;

	public Like() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Like(long id, User user, Post post) {
		super();
		this.id = id;
		this.user = user;
		this.post = post;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
		return "Like [Id=" + this.id + ", user=" + user + ", post=" + post + "]";
	}
 
 
}
