package com.egen.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ratings")
@NamedQueries({
	@NamedQuery(name = "Rating.findByMovieAndUser", query = "SELECT r FROM Rating r WHERE r.movie = :movie and r.user = :user"),
	@NamedQuery(name = "Rating.findByMovie", query = "SELECT r FROM Rating r WHERE r.movie = :movie")
})
public class Rating {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;

	private Long value;

	@OneToOne
	private Movie movie;

	@ManyToOne
	private User user;

	public Rating() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
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
