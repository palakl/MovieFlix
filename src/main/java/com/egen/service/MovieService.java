package com.egen.service;

import java.util.List;

import com.egen.model.Movie;

public interface MovieService {

	public Movie getMovieById(String id);

	public List<Movie> getMovieByType(String type);
	
	public Movie createMovie(Movie movie);

}
