package com.egen.service;

import com.egen.exception.UserAlreadyExistsException;
import com.egen.exception.UserNotFoundException;
import com.egen.model.User;

public interface UserService {

	public User createUser(User user) throws UserAlreadyExistsException;
	
	public User getUserByEmail(String email) throws UserNotFoundException;
	
	public User getUserById(String id) throws UserNotFoundException;

}
