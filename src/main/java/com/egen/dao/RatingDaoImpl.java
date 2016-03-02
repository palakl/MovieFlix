package com.egen.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.egen.model.Movie;
import com.egen.model.Rating;
import com.egen.model.User;

@Repository
public class RatingDaoImpl implements RatingDao {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Rating addRating(Rating rating) {
		return entityManager.merge(rating);
	}

	@Override
	public Rating findRatingByMovieAndUser(Movie movie, User user) {
		TypedQuery<Rating> query = entityManager.createNamedQuery("Rating.findByMovieAndUser", Rating.class);
		query.setParameter("movie", movie);
		query.setParameter("user", user);
		List<Rating> ratings = query.getResultList();
    	if(ratings != null && ratings.size() == 1) {
    		return ratings.get(0);
    	}
    	else {
    		return null;
    	}
	}

}
