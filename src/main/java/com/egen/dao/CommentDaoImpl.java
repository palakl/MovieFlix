package com.egen.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.egen.model.Comment;
import com.egen.model.Movie;
import com.egen.model.User;

@Repository
public class CommentDaoImpl implements CommentDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Comment addComment(Comment comment) {
		return entityManager.merge(comment);
	}

	@Override
	public Comment findCommentByMovieAndUser(Movie movie, User user) {
		TypedQuery<Comment> query = entityManager.createNamedQuery(
				"Comment.findByMovieAndUser", Comment.class);
		query.setParameter("movie", movie);
		query.setParameter("user", user);
		List<Comment> comments = query.getResultList();
		if (comments != null && comments.size() == 1) {
			return comments.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Comment> findCommentsByMovie(Movie movie) {
		TypedQuery<Comment> query = entityManager.createNamedQuery(
				"Comment.findByMovie", Comment.class);
		query.setParameter("movie", movie);
		List<Comment> comments = query.getResultList();
		if (comments != null) {
			return comments;
		} else {
			return null;
		}
	}

}
