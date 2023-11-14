package com.projectFilm.demo.movies.repository;


import lombok.Data;

import java.util.List;


@Data
public class MoviesSearchCondition {

	private Integer prdtYearGoe;
	private Integer prdtYearLoe;
	private Integer openDtGoe;
	private Integer openDtLoe;

	private List<String> typeNms;
	private List<String> prdtStatNms;
	private List<String> repNationNms;
	private List<Integer> genreIds;
}
