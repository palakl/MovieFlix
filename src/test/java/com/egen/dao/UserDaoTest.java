package com.egen.dao;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

import com.egen.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserDaoTest {

	@Mock
	private EntityManager entityManager;

	@InjectMocks
	private UserDao userDao = new UserDaoImpl();

	@Mock
	private TypedQuery<User> query;

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

	@Test
	public void testCreateUser() {
		userDao.createUser(user);
		Mockito.verify(entityManager).persist(user);
	}

	@Test
	public void testFindUserByEmail() {
		List<User> expected = Arrays.asList(user);
		Mockito.when(
				entityManager.createNamedQuery("User.findByEmail", User.class))
				.thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		User actual = userDao.findUserByEmail(user.getEmail());
		Assert.assertEquals(user, actual);

	}

	@Test
	public void testFindUserByEmailNull() {

		Mockito.when(
				entityManager.createNamedQuery("User.findByEmail", User.class))
				.thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);

		User actual = userDao.findUserByEmail(user.getEmail());
		Assert.assertEquals(null, actual);
	}

	@Test
	public void testFindUserById() {

		Mockito.when(entityManager.find(User.class, user.getId())).thenReturn(
				user);
		User actual = userDao.findUserById(user.getId());
		Assert.assertEquals(user, actual);

	}

	@Test
	public void testFindUserByEmailAndPassword() {
		List<User> expected = Arrays.asList(user);
		Mockito.when(
				entityManager.createNamedQuery("User.findByEmailAndPassword",
						User.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);

		User actual = userDao.findUserByEmailAndPassword(user.getEmail(),
				user.getPassword());
		Assert.assertEquals(user, actual);

	}

	@Test
	public void testFindUserByEmailAndPasswordNull() {
		Mockito.when(
				entityManager.createNamedQuery("User.findByEmailAndPassword",
						User.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);

		User actual = userDao.findUserByEmailAndPassword(user.getEmail(),
				user.getPassword());
		Assert.assertEquals(null, actual);

	}

	@Configuration
	public static class TestConfig {
		
	}

}
