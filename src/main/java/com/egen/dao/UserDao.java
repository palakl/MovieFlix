package com.egen.dao;

import com.egen.model.User;

public interface UserDao {
	
	public User createUser(User user);
	
	public User getUserByEmail(String email);
	
	public User getUserById(String id);

}
