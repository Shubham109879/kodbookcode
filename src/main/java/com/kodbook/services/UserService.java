package com.kodbook.services;

import java.util.List;

import com.kodbook.entities.User;

public interface UserService 
{
	void addUser(User user);

	boolean userExists(String username, String email);

	boolean validateUser(String username, String password);

	User getUser(String username);

	void updateUser(User user);

	boolean userNameExists(String username);
	
	public User getUserByEmail(String email); 
	
	public boolean userEmailExists(String email);
	
	public List<User> searchUser(String username);
	
}
