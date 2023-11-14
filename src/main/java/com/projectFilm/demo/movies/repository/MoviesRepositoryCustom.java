package com.projectFilm.demo.movies.repository;

import com.projectFilm.demo.movies.entity.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MoviesRepositoryCustom {
	List<Movies> search(MoviesSearchCondition condition);
	Page<Movies> searchPage(MoviesSearchCondition condition, Pageable pageable);
}
