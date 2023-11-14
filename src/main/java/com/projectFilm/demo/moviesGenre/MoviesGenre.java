package com.projectFilm.demo.moviesGenre;

import com.projectFilm.demo.movies.Movies;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class MoviesGenre {

	@Id
	public String movieCd;

	public Integer genreId;
	public String mainGenre;
	public String otherGenres;

	@OneToOne
//	@JoinColumn(name = "movie_fk")
	@JoinColumn(name = "movieCd", referencedColumnName = "movieCd")
	private Movies movies;

}
