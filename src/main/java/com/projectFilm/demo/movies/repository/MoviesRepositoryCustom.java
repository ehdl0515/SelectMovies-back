package com.projectFilm.demo.movies.repository;

import com.projectFilm.demo.movies.entity.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

public interface MoviesRepositoryCustom {
//	List<Movies> search(MoviesSearchCondition condition);
	Page<Movies> findAllBy(MoviesSearchCondition condition, Pageable pageable);
}
