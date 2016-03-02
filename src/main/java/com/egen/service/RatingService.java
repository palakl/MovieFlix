package com.egen.service;

import com.egen.model.Movie;
import com.egen.model.Rating;
import com.egen.model.User;

public interface RatingService {
	
	public Rating addRating(Rating rating);
	
	public Rating findRatingByMovieAndUser(Movie movie, User user);

}
