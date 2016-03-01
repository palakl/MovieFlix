package com.egen.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.dao.UserDao;
import com.egen.exception.UserAlreadyExistsException;
import com.egen.exception.UserNotFoundException;
import com.egen.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public User createUser(User user) throws UserAlreadyExistsException {
		User existing = userDao.getUserByEmail(user.getEmail());
		if (existing == null) {
			return userDao.createUser(user);
		} else {
			throw new UserAlreadyExistsException();
		}
	}

	@Override
	public User getUserByEmail(String email) throws UserNotFoundException {
		User user = userDao.getUserByEmail(email);
		if (user != null) {
			return user;
		} else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public User getUserById(String id) throws UserNotFoundException {
		User user = userDao.getUserById(id);
		if (user != null) {
			return user;
		} else {
			throw new UserNotFoundException();
		}
	}

}
