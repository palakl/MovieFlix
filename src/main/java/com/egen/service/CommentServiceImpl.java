package com.egen.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.dao.CommentDao;
import com.egen.model.Comment;
import com.egen.model.Movie;
import com.egen.model.User;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao commentDao;

	@Override
	public Comment addComment(Comment comment) {
		return commentDao.addComment(comment);
	}

	@Override
	public Comment findCommentByMovieAndUser(Movie movie, User user) {
		return commentDao.findCommentByMovieAndUser(movie, user);
	}

	@Override
	public List<Comment> findCommentsByMovie(Movie movie) {
		return commentDao.findCommentsByMovie(movie);
	}

}
