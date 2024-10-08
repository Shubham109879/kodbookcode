package com.kodbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodbook.entities.User;
import com.kodbook.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService
{
   @Autowired
   UserRepository repo;

	@Override
	public void addUser(User user) 
	{
		repo.save(user);	
	}

	@Override
	public boolean userExists(String username, String email) 
	{
		User user1=repo.findByUsername(username);
		User user2=repo.findByEmail(email);
		
		if(user1!=null || user2!=null)
		{
		 return true;	
		}
		
		return false;
	}

	@Override
	public boolean validateUser(String username, String password) 
	{
		String dbPass = repo.findByUsername(username).getPassword();
		if(password.equals(dbPass)) 
		{
			return true;
		}
		return false;
	}

	@Override
	public User getUser(String username) 
	{
	  return repo.findByUsername(username);	
	}
	
	@Override
	public User getUserByEmail(String email) 
	{
	  return repo.findByEmail(email);	
	}

	@Override
	public void updateUser(User user) 
	{
	 repo.save(user);	
	}

	@Override
	public boolean userNameExists(String username) {
		
		User user=repo.findByUsername(username);
		
		if(user!=null)
		{
		 return true;	
		}
		
		return false;
	}
	
	@Override
	public boolean userEmailExists(String email) 
	{
		User user=repo.findByEmail(email);
		
		if(user!=null)
		{
		 return true;	
		}
		
		return false;
	}

	@Override
	public List<User> searchUser(String username) {
	     
		return repo.findByUsernameContainingIgnoreCase(username);
	}
	
	

}
