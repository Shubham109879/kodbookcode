package com.kodbook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodbook.entities.Like;
import com.kodbook.entities.Post;
import com.kodbook.entities.User;
import com.kodbook.repositories.LikeRepository;
import com.kodbook.repositories.PostRepository;
import com.kodbook.repositories.UserRepository;

@Service
public class PostServiceImplementation implements PostService
{
	@Autowired
	PostRepository repo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	LikeRepository likeRepo;

	@Override
	public void createPost(Post post) {
		repo.save(post);
		
	}

	@Override
	public List<Post> getAllPosts() {
		return repo.findAll();
	}

	@Override
	public List<Post> fetchAllPosts() {
		return repo.findAll();
	}

	@Override
	public Post getPost(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public void updatePost(Post post) {
		repo.save(post);
		
	}

//	public boolean likeExists(Long id) {
//		Post post=repo.findById(id).get();
//		
//		if(post.getLikes()==1)
//		{
//		 return true;	
//		}
//		
//		return false;
//	}

	@Override
	public void likePost(Long postId, Long userId) {
		
		Post post=repo.findById(postId).get();
		User user=userRepo.findById(userId).get();
		
		Optional<Like> likeExists=likeRepo.findByUserAndPost(user, post);
		
		if(likeExists.isPresent())
		{
		 likeRepo.delete(likeExists.get());
		 post.setLikes(post.getLikes()-1);
		}
		
		else
		{
		 Like like=new Like();
		 like.setUser(user);
		 like.setPost(post);
		 likeRepo.save(like);
		 post.setLikes(post.getLikes()+1);
		}
		
		repo.save(post);
	}
	
	

}
