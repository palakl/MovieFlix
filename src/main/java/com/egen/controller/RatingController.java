package com.egen.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.egen.exception.MovieNotFoundException;
import com.egen.exception.UserNotFoundException;
import com.egen.model.Movie;
import com.egen.model.Rating;
import com.egen.model.User;
import com.egen.service.MovieService;
import com.egen.service.RatingService;
import com.egen.service.UserService;

@RestController
@RequestMapping(value = "/ratings")
public class RatingController {

	@Autowired
	UserService userService;

	@Autowired
	RatingService ratingService;

	@Autowired
	MovieService movieService;

	@RequestMapping(value = "{movieid}/{userid}/{ratingvalue}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add a new rating for a movie", notes = "Returns a rating for a movie")
	public Rating addRating(@PathVariable("movieid") String movieId,
			@PathVariable("userid") String userId,
			@PathVariable("ratingvalue") Long value) throws UserNotFoundException, MovieNotFoundException {

		Movie movie = movieService.findMovieById(movieId);
		User user = userService.findUserById(userId);
		Rating rating = ratingService.findRatingByMovieAndUser(movie, user);
		if (rating == null) {
			rating = new Rating();
			rating.setMovie(movie);
			rating.setUser(user);

		}
		rating.setValue(value);
		return ratingService.addRating(rating);
	}

	@RequestMapping(value = "{movieid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find rating for a movie", notes = "Create and return the rating for the movie")
	public Long findRatingByMovie(@PathVariable("movieid") String movieId) throws MovieNotFoundException {

		Movie movie = movieService.findMovieById(movieId);
		List<Rating> ratings = ratingService.findRatingByMovie(movie);
		long value = 0;
		if (ratings != null) {
			for (Rating rating : ratings) {
				value += rating.getValue();
			}
			value = (value / ratings.size());
		}

		return value;
	}

}
