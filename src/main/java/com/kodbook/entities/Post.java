package com.kodbook.entities;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Post 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String caption;
	
	@Column(nullable=false)
	private int likes;
	
	
	//private List<String> comments;
	
	// Establishing a one-to-many relationship with comments
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likeList = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] photo;
	
	public String getPhotoBase64() {
        if (photo == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(photo);
    }

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(Long id, String caption, int likes, List<Comment> comments, List<Like> likeList, User user,
			byte[] photo) {
		super();
		this.id = id;
		this.caption = caption;
		this.likes = likes;
		this.comments = comments;
		this.likeList = likeList;
		this.user = user;
		this.photo = photo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Like> getLikeList() {
		return likeList;
	}

	public void setLikeList(List<Like> likeList) {
		this.likeList = likeList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", caption=" + caption + ", likes=" + likes + ", comments=" + comments + ", likeList="
				+ likeList + ", user=" + user + ", photo=" + Arrays.toString(photo) + "]";
	}

	
	
}
