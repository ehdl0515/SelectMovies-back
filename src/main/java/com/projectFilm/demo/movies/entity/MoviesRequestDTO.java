package com.projectFilm.demo.movies.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MoviesRequestDTO {

	private Integer prdtYearGoe;
	private Integer prdtYearLoe;
	private Integer openDtGoe;
	private Integer openDtLoe;
	private List<String> typeNms;
	private List<String> prdtStatNms;
	private List<String> repNationNms;
	private List<Integer> genreIds;
	private Integer page;
	private Integer size;
	private String sortColumn;
	private Boolean sortDesc;

}
