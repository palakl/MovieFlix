package com.egen.dao;

import com.egen.exception.UserNotFoundException;
import com.egen.model.User;

public interface UserDao {
	
	public User createUser(User user);
	
	public User findUserByEmail(String email);
	
	public User findUserById(String id);
	
	public User findUserByEmailAndPassword(String email, String password) throws UserNotFoundException;

}
