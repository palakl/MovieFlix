package com.egen.dao;

import java.util.List;

import com.egen.model.Movie;
import com.egen.model.Rating;
import com.egen.model.User;

public interface RatingDao {
	
	public Rating addRating(Rating rating);
	
	public Rating findRatingByMovieAndUser(Movie movie, User user);
	
	public List<Rating> findRatingByMovie(Movie movie);

}
