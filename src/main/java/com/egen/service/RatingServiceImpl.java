package com.egen.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.dao.MovieDao;
import com.egen.dao.RatingDao;
import com.egen.dao.UserDao;
import com.egen.model.Movie;
import com.egen.model.Rating;
import com.egen.model.User;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {

	@Autowired
	private MovieDao movieDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RatingDao ratingDao;

	@Override
	public Rating addRating(Rating rating) {
		return ratingDao.addRating(rating);
	}

	@Override
	public Rating findRatingByMovieAndUser(Movie movie, User user) {
		return ratingDao.findRatingByMovieAndUser(movie, user);
	}

	@Override
	public List<Rating> findRatingByMovie(Movie movie) {
		return ratingDao.findRatingByMovie(movie);
	}

}
