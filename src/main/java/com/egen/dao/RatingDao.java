package com.egen.dao;

import com.egen.model.Movie;
import com.egen.model.Rating;
import com.egen.model.User;

public interface RatingDao {
	
	public Rating addRating(Rating rating);
	
	public Rating findRatingByMovieAndUser(Movie movie, User user);

}
