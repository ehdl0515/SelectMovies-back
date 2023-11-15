package com.projectFilm.demo.movies.repository;

import com.projectFilm.demo.movies.entity.Movies;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.projectFilm.demo.movies.entity.QMovies.movies;
import static com.projectFilm.demo.movies.entity.QMoviesGenre.moviesGenre;
import static org.springframework.util.StringUtils.hasText;

public abstract class MoviesRepositoryImpl implements MoviesRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public MoviesRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public Page<Movies> findAllBy(MoviesSearchCondition condition, Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		builder.and(movies.movieCd.eq(moviesGenre.movieCd));

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


		List<Movies> content = queryFactory
				.select(movies)
				.from(movies, moviesGenre)
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		JPAQuery<Long> countQuery = queryFactory
				.select(movies.count())
				.from(movies, moviesGenre)
				.where(builder);

		return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetch().size());
	}
}
