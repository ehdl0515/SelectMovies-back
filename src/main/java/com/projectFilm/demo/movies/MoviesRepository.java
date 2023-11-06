package com.projectFilm.demo.movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MoviesRepository extends JpaRepository<Movies, String> {
	@Query(value = "SELECT * FROM Movies LIMIT :limit", nativeQuery = true)
	List<Movies> findMoviesWithLimit(int limit);
}
