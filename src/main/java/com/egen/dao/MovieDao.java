package com.egen.dao;

import java.util.List;

import com.egen.model.Movie;

public interface MovieDao {

	public Movie getMovieById(String id);

	public List<Movie> getMovieByType(String type);
	
	public Movie createMovie(Movie movie);

}
