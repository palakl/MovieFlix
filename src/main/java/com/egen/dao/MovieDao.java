package com.egen.dao;

import java.util.List;

import com.egen.model.Movie;

public interface MovieDao {

	public Movie findMovieById(String id);

	public List<Movie> findMovieByType(String type);
	
	public List<Movie> findAllMovie();
	
	public Movie createMovie(Movie movie);

}
