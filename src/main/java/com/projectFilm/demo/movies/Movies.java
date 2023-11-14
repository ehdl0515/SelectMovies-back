package com.projectFilm.demo.movies;

import com.projectFilm.demo.moviesGenre.MoviesGenre;
import lombok.*;

import javax.persistence.*;



@NoArgsConstructor
@Entity
@Getter
public class Movies {

	@Id
	public String movieCd;

	public String movieNm;
	public String movieNmEn;
	public short prdtYear;
	public int openDt;
	public String typeNm;
	public String prdtStatNm;
	public String nationAlt;
	public String genreAlt;
	public String repNationNm;
	public String repGenreNm;

	@OneToOne(mappedBy = "movies")
	private MoviesGenre moviesGenre;
}

