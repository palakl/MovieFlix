package com.egen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.egen.model.Movie;
import com.egen.service.MovieService;

@RestController
@RequestMapping(value="/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@RequestMapping(value="{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Movie getMovieById(@PathVariable("id") String id) {
		System.out.println(id);
		return movieService.getMovieById(id);
	}

	/*@RequestMapping(value="{type}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Movie> getMovieByType(@PathVariable("type") String type) {
		return movieService.getMovieByType(type);
	}*/
}
