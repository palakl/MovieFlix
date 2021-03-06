package com.egen.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "comments")
@NamedQueries({
	@NamedQuery(name = "Comment.findByMovieAndUser", query = "SELECT r FROM Comment r WHERE r.movie = :movie and r.user = :user"),
	@NamedQuery(name = "Comment.findByMovie", query = "SELECT r FROM Comment r WHERE r.movie = :movie")
})
public class Comment {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;

	private String comment;

	@ManyToOne
	private Movie movie;

	@ManyToOne
	private User user;

	public Comment() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
