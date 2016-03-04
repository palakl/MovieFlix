package com.egen.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.dao.MovieDao;
import com.egen.exception.MovieNotFoundException;
import com.egen.model.Movie;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao movieDao;

	@Override
	public Movie findMovieById(String id) throws MovieNotFoundException {
		Movie movie = movieDao.findMovieById(id);
		if(movie == null){
			throw new MovieNotFoundException();
		} else {
			return movie;
		}
	}

	@Override
	public List<Movie> findMovieByType(String type) {
		return movieDao.findMovieByType(type);
	}

	@Override
	public Movie createMovie(Movie movie) {
		return movieDao.createMovie(movie);
		
	}

	@Override
	public List<Movie> findAllMovie() {
		return movieDao.findAllMovie();
	}

	@Override
	public Movie updateMovie(String id, Movie movie) throws MovieNotFoundException {
		Movie existing = movieDao.findMovieById(id);
		if(existing == null){
			throw new MovieNotFoundException();
		} else {
			return movieDao.updateMovie(movie);
		}
		
	}

	@Override
	public Movie deleteMovie(String id) throws MovieNotFoundException {
		Movie existing = movieDao.findMovieById(id);
		if(existing == null){
			throw new MovieNotFoundException();
		} else {
			return movieDao.deleteMovie(existing);
		}
	}

}
