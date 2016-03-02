package com.egen.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.egen.model.Movie;
import com.egen.service.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find Movie By Id", notes = "Returns a movie by it's id if it exists.")
	public Movie findMovieById(@PathVariable("id") String id) {
		return movieService.findMovieById(id);
	}

	@RequestMapping(value = "type/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find Movies By Type", notes = "Returns a list of movies by it's type.")
	public List<Movie> findMovieByType(@PathVariable("type") String type) {
		return movieService.findMovieByType(type);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find All Movie", notes = "Returns a list of movies")
	public List<Movie> findAllMovie() {
		return movieService.findAllMovie();
	}
}
