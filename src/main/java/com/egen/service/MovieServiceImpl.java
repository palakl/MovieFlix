package com.egen.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.dao.MovieDao;
import com.egen.model.Movie;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao movieDao;

	@Override
	public Movie findMovieById(String id) {
		return movieDao.findMovieById(id);
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

}
