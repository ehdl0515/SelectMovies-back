package com.projectFilm.demo.movies.repository;

import com.projectFilm.demo.movies.entity.Movies;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MoviesRepository extends JpaRepository<Movies, String> {

//	@Query(value = "SELECT * FROM Movies LIMIT :limit", nativeQuery = true)
//	List<Movies> findMoviesWithLimit(int limit);

//	List<Movies> search(MoviesSearchCondition condition);
	Page<Movies> searchPage(MoviesSearchCondition condition, Pageable pageable);
}
