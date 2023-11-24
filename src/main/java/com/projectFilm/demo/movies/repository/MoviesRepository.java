package com.projectFilm.demo.movies.repository;

import com.projectFilm.demo.movies.entity.DailyBoxOffice;
import com.projectFilm.demo.movies.entity.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MoviesRepository extends JpaRepository<Movies, String> {

	@Query(value = "SELECT * FROM Daily_Box_Office where dateStamp = :date order by movieRank", nativeQuery = true)
	List<DailyBoxOffice> searchDailyRank(@Param("date") int date);

	List<Movies> search(MoviesSearchCondition condition);
	Page<Movies> searchPage(MoviesSearchCondition condition, Pageable pageable);
}
