package com.projectFilm.demo.moviesGenre;

import com.projectFilm.demo.movies.Movies;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@NoArgsConstructor
@Entity
@Getter
public class MoviesGenre {

	@Id
	public String movieCd;

	public Integer genreId;
	public String mainGenre;
	public String otherGenre;

	@OneToOne(mappedBy = "moviesGenre")
	private Movies movies;

}
