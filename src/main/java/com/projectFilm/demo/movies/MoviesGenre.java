package com.projectFilm.demo.movies;

import javax.persistence.Id;

public class MoviesGenre {

	@Id
	public String movieCd;

	public Integer genreId;
	public String mainGenre;
	public String otherGenre;
}
