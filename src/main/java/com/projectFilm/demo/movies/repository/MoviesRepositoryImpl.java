package com.projectFilm.demo.movies.repository;

import com.projectFilm.demo.movies.entity.Movies;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.projectFilm.demo.movies.QMovies.movies;
import static com.projectFilm.demo.moviesGenre.QMoviesGenre.moviesGenre;

public abstract class MoviesRepositoryImpl implements MoviesRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public MoviesRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public Page<Movies> searchPage(MoviesSearchCondition condition, Pageable pageable) {

		List<Movies> content = queryFactory
				.select(movies)
				.from(movies, moviesGenre)
				.where(
						moviesGenre.movieCd.eq(movies.movieCd),
						moviesGenre.genreId.notIn(condition.getgenreIds()),
						movies.prdtYear.notIn(condition.getprdtYears())
				)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		JPAQuery<Long> countQuery = queryFactory
				.select(movies.count())
				.from(movies, moviesGenre)
				.where(
						moviesGenre.movieCd.eq(movies.movieCd),
						moviesGenre.genreId.notIn(condition.getgenreIds()),
						movies.prdtYear.notIn(condition.getprdtYears())
				);
		return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetch().size());
	}
}
