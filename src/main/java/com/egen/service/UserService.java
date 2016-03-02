package com.egen.service;

import com.egen.exception.UserAlreadyExistsException;
import com.egen.exception.UserNotFoundException;
import com.egen.model.User;

public interface UserService {

	public User createUser(User user) throws UserAlreadyExistsException;
	
	public User findUserByEmail(String email) throws UserNotFoundException;
	
	public User findUserById(String id) throws UserNotFoundException;
	
	public User findUserByEmailAndPassword(String email, String password) throws UserNotFoundException;

}
