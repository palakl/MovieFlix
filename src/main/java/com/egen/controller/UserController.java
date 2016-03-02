package com.egen.controller;

import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egen.exception.UserAlreadyExistsException;
import com.egen.exception.UserNotFoundException;
import com.egen.model.Movie;
import com.egen.model.Rating;
import com.egen.model.User;
import com.egen.service.MovieService;
import com.egen.service.RatingService;
import com.egen.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	RatingService ratingService;

	@Autowired
	MovieService movieService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find User By Id", notes = "Returns a user by it's id if it exists.")
	public User findUserById(@PathVariable("id") String id)
			throws UserNotFoundException {
		return userService.findUserById(id);
	}

	@RequestMapping(value = "email/{email}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find User By Email", notes = "Returns a user by it's email if it exists.")
	public User findUserByEmail(@PathVariable("email") String email,
			HttpServletRequest request) throws UserNotFoundException {
		return userService.findUserByEmail(email);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create new user", notes = "Create and return the new user")
	public User createUser(@RequestBody User user)
			throws UserAlreadyExistsException {
		return userService.createUser(user);
	}

	@RequestMapping(value = "signin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Login", notes = "On successful authentication user will be logged in")
	public User signInUser(@RequestParam("email") String email,
			@RequestParam("password") String password)
			throws UserNotFoundException {

		User user = userService.findUserByEmailAndPassword(email, password);
		if (user != null) {
			return user;
		} else {
			return null;
		}

	}

	@RequestMapping(value = "{movieid}/{userid}/{rating}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add a new rating for a movie", notes = "Create and return the rating for the movie")
	public Rating addRating(@PathVariable("movieid") String movieId,
			@PathVariable("userid") String userId,
			@PathVariable("rating") Long value) throws UserNotFoundException {

		Movie movie = movieService.findMovieById(movieId);
		User user = userService.findUserById(userId);
		Rating rating = ratingService.findRatingByMovieAndUser(movie, user);
		if (rating == null) {
			rating = new Rating();
			rating.setMovie(movie);
			rating.setUser(user);
			rating.setValue(value);

		} else {
			rating.setValue((rating.getValue() + value) / 2);
		}
		return ratingService.addRating(rating);
	}

}
