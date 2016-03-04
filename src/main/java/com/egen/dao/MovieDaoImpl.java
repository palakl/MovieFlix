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
	public Movie findMovieById(String id) {
		return entityManager.find(Movie.class, id);
	}

	@Override
	public List<Movie> findMovieByType(String type) {
		List<Movie> movies = entityManager
				.createQuery("from Movie where type= :type", Movie.class)
				.setParameter("type", type).getResultList();
		return movies;
	}

	@Override
	public Movie createMovie(Movie movie) {
		entityManager.persist(movie);
		return movie;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> findAllMovie() {
		List<Movie> movies = entityManager.createQuery("from Movie").getResultList();
		return movies;
	}

	@Override
	public Movie updateMovie(Movie movie) {
		return entityManager.merge(movie);
	}

	@Override
	public Movie deleteMovie(Movie movie) {
		entityManager.remove(movie);
		return movie;
	}
}
