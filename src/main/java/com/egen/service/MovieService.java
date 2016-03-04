package com.egen.service;

import java.util.List;

import com.egen.exception.MovieNotFoundException;
import com.egen.model.Movie;

public interface MovieService {

	public Movie findMovieById(String id) throws MovieNotFoundException;

	public List<Movie> findMovieByType(String type);

	public Movie createMovie(Movie movie);

	public List<Movie> findAllMovie();
	
	public Movie updateMovie(String id, Movie movie) throws MovieNotFoundException;

	public Movie deleteMovie(String id) throws MovieNotFoundException;

}
