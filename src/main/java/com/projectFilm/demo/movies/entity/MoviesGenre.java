package com.projectFilm.demo.movies.entity;

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
}
