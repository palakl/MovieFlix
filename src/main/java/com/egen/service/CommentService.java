package com.egen.service;

import java.util.List;

import com.egen.model.Comment;
import com.egen.model.Movie;
import com.egen.model.User;

public interface CommentService {
	
	public Comment addComment(Comment comment);
	
	public Comment findCommentByMovieAndUser(Movie movie, User user);
	
	public List<Comment> findCommentsByMovie(Movie movie);



}
