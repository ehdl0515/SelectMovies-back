package com.projectFilm.demo.movies.repository;


import lombok.Data;

@Data
public class MoviesSearchCondition {

		private String prdtStatNm;
		private Integer genreId;
		private Integer openDtGoe;
		private Integer openDtLoe;

}
