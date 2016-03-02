package com.egen.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.egen.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User createUser(User user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		TypedQuery<User> query = entityManager.createNamedQuery("User.findByEmail", User.class);
    	query.setParameter("email", email);
    	List<User> users = query.getResultList();
    	if(users != null && users.size() == 1) {
    		return users.get(0);
    	}
    	else {
    		return null;
    	}
	}

	@Override
	public User findUserById(String id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public User findUserByEmailAndPassword(String email, String password) {
		TypedQuery<User> query = entityManager.createNamedQuery("User.findByEmailAndPassword", User.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		User user = query.getSingleResult();
		if(user != null){
			return user;
		} else {
			return null;
		}
		
	}

}
