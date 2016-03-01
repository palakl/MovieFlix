package com.egen.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.egen.model.Movie;

@Repository
public class MovieDaoImpl implements MovieDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Movie getMovieById(String id) {
		return entityManager.find(Movie.class, id);
	}

	@Override
	public List<Movie> getMovieByType(String type) {
		List<Movie> movies = entityManager
				.createQuery("from Movie where type= :type", Movie.class)
				.setParameter("type", type).getResultList();
		return movies;
	}

	@Override
	public Movie createMovie(Movie movie) {
		return entityManager.merge(movie);
	}
}
