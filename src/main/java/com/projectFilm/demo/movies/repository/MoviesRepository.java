package com.projectFilm.demo.movies.repository;

import com.projectFilm.demo.movies.entity.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MoviesRepository extends JpaRepository<Movies, String>, MoviesRepositoryCustom {
	@Query(value = "SELECT * FROM Movies LIMIT :limit", nativeQuery = true)
	List<Movies> findMoviesWithLimit(int limit);

	@Query(value = "SELECT * FROM Movies MV JOIN MoviesGenre MVGR ON MV.movieCd = MVGR.movieCd WHERE MVGR.genreId != :GenreId", nativeQuery = true)
	Page<Movies> findMoviesByGenreNotContainingPage(int GenreId, Pageable pageable);

	@Query(value = "SELECT * FROM Movies MV JOIN MoviesGenre MVGR ON MV.movieCd = MVGR.movieCd WHERE MVGR.genreId != :GenreId", nativeQuery = true)
	List<Movies> findMoviesByGenreNotContaining(int GenreId);

	@Query(value = "SELECT count(MV.movieCd) FROM Movies MV INNER JOIN MoviesGenre MVGR ON MV.movieCd = MVGR.movieCd WHERE MVGR.genreId != :GenreId", nativeQuery = true)
	long countMoviesByGenreNotContaining(int GenreId);
}
