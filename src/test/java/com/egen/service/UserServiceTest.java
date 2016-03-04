package com.egen.service;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.egen.dao.UserDao;
import com.egen.exception.UserAlreadyExistsException;
import com.egen.exception.UserNotFoundException;
import com.egen.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserServiceTest {

	@Mock
	private UserDao userDao;

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	private User user;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setEmail("dummy@dummy.com");
		user.setFirstName("Dummy");
		user.setLastName("User");
		user.setId(UUID.randomUUID().toString());
	}

	@Test(expected = UserAlreadyExistsException.class)
	public void testCreateUserException() throws UserAlreadyExistsException {
		Mockito.when(userDao.findUserByEmail(user.getEmail())).thenReturn(user);
		userService.createUser(user);
	}

	@Test
	public void testCreateUser() throws UserAlreadyExistsException {
		Mockito.when(userDao.findUserByEmail(user.getEmail())).thenReturn(null);
		userService.createUser(user);
		Mockito.verify(userDao).createUser(user);
	}

	@Test
	public void testFindUserByEmail() throws UserNotFoundException {
		Mockito.when(userDao.findUserByEmail(user.getEmail())).thenReturn(user);
		User actual = userService.findUserByEmail(user.getEmail());
		Assert.assertEquals(user, actual);

	}

	@Test(expected = UserNotFoundException.class)
	public void testFindUserByEmailExcpetion() throws UserNotFoundException {
		Mockito.when(userDao.findUserByEmail(user.getEmail())).thenReturn(null);
		userService.findUserByEmail(user.getEmail());
	}

	@Test
	public void testFindUserById() throws UserNotFoundException {
		Mockito.when(userDao.findUserById(user.getId())).thenReturn(user);
		User actual = userService.findUserById(user.getId());
		Assert.assertEquals(user, actual);

	}

	@Test(expected = UserNotFoundException.class)
	public void testFindUserByIdExcpetion() throws UserNotFoundException {
		Mockito.when(userDao.findUserById(user.getId())).thenReturn(null);
		userService.findUserById(user.getId());
	}

	@Test
	public void testFindUserByEmailAndPassword() throws UserNotFoundException {
		Mockito.when(
				userDao.findUserByEmailAndPassword(user.getEmail(),
						user.getPassword())).thenReturn(user);
		User actual = userService.findUserByEmailAndPassword(user.getEmail(),
				user.getPassword());
		Assert.assertEquals(user, actual);
	}

	@Test(expected = UserNotFoundException.class)
	public void testFindUserByEmailAndPasswordException()
			throws UserNotFoundException {
		Mockito.when(
				userDao.findUserByEmailAndPassword(user.getEmail(),
						user.getPassword())).thenReturn(null);
		userService.findUserByEmailAndPassword(user.getEmail(),
				user.getPassword());
	}

	@Configuration
	public static class TestConfig {

	}

}
