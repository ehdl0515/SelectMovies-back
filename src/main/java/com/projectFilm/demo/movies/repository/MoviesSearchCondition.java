package com.projectFilm.demo.movies.repository;

import com.projectFilm.demo.movies.QMovies;
import com.projectFilm.demo.movies.entity.Movies;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import java.util.List;

import static com.projectFilm.demo.movies.QMovies.movies;
import static com.projectFilm.demo.moviesGenre.QMoviesGenre.moviesGenre;
import static org.springframework.util.StringUtils.hasText;

@Data
@RequiredArgsConstructor
public class MoviesSearchCondition {

		private final EntityManager em;
		private final JPAQueryFactory queryFactory;

		private String prdtStatNm;
		private Integer genreId;
		private Integer openDtGoe;
		private Integer openDtLoe;

		public List<Movies> searchByBuilder(MoviesSearchCondition condition) {
			BooleanBuilder builder = new BooleanBuilder();

			if (hasText(condition.getPrdtStatNm())) {
				builder.and(movies.prdtStatNm.eq(condition.getPrdtStatNm()));
			}

			if (condition.getGenreId() != null) {
				builder.and(movies.genreAlt.eq(String.valueOf(condition.getGenreId())));
			}

			if (condition.getOpenDtGoe() != null) {
				builder.and(movies.openDt.goe(condition.getOpenDtGoe()));
			}
			if (condition.getOpenDtLoe() != null) {
				builder.and(movies.openDt.loe(condition.getOpenDtLoe()));
			}

			builder.and(movies.movieCd.eq(moviesGenre.movieCd));

			return queryFactory
					.select(movies)
					.from(movies, moviesGenre)
					.where(builder)
					.fetch();
		}


	}
