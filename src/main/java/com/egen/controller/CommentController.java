package com.egen.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.egen.exception.UserNotFoundException;
import com.egen.model.Comment;
import com.egen.model.Movie;
import com.egen.model.User;
import com.egen.service.CommentService;
import com.egen.service.MovieService;
import com.egen.service.UserService;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {

	@Autowired
	UserService userService;

	@Autowired
	MovieService movieService;

	@Autowired
	CommentService commentService;

	@RequestMapping(value = "{movieid}/{userid}/{comment}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add a new comment for a movie", notes = "Returns a comment for a movie")
	public Comment addRating(@PathVariable("movieid") String movieId,
			@PathVariable("userid") String userId,
			@PathVariable("comment") String value) throws UserNotFoundException {

		Movie movie = movieService.findMovieById(movieId);
		User user = userService.findUserById(userId);
		Comment comment = commentService.findCommentByMovieAndUser(movie, user);
		if (comment == null) {
			comment = new Comment();
			comment.setMovie(movie);
			comment.setUser(user);

		}
		comment.setComment(value);
		return commentService.addComment(comment);

	}

	@RequestMapping(value = "{movieid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find comments for a movie", notes = "Return a list of comments for a movie")
	public List<Comment> findCommentsByMovie(
			@PathVariable("movieid") String movieId) {

		Movie movie = movieService.findMovieById(movieId);
		List<Comment> comments = commentService.findCommentsByMovie(movie);
		return comments;
	}

}
