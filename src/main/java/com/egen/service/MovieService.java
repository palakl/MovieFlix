package com.egen.service;

import java.util.List;

import com.egen.model.Movie;

public interface MovieService {

	public Movie findMovieById(String id);

	public List<Movie> findMovieByType(String type);

	public Movie createMovie(Movie movie);

	public List<Movie> findAllMovie();

}
