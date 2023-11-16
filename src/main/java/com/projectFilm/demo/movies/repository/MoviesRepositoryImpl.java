package com.projectFilm.demo.movies.repository;

import com.projectFilm.demo.movies.entity.Movies;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.projectFilm.demo.movies.entity.QMovies.movies;
import static com.projectFilm.demo.movies.entity.QMoviesGenre.moviesGenre;
import static org.springframework.util.StringUtils.hasText;

@Repository
class MoviesRepositoryImpl extends QuerydslRepositorySupport{

	private final JPAQueryFactory jpaQueryFactory;

	public MoviesRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
		super(Movies.class);
		this.jpaQueryFactory = jpaQueryFactory;
	}

	public Page<Movies> searchPage(MoviesSearchCondition condition, Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		builder.and(movies.movieCd.eq(moviesGenre.movieCd));

		if (hasText(condition.getPrdtStatNm())) {
			builder.and(movies.prdtStatNm.eq(condition.getPrdtStatNm()));
		}

		if (condition.getGenreId() != null) {
			builder.and(moviesGenre.genreId.eq(condition.getGenreId()));
		}

		if (condition.getOpenDtGoe() != null) {
			builder.and(movies.openDt.goe(condition.getOpenDtGoe()));
		}
		if (condition.getOpenDtLoe() != null) {
			builder.and(movies.openDt.loe(condition.getOpenDtLoe()));
		}

		System.out.println("pageable = " + pageable);
		List<Movies> content = jpaQueryFactory
				.select(movies)
				.from(movies, moviesGenre)
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		System.out.println("content = " + content);
		JPAQuery<Long> countQuery = jpaQueryFactory
				.select(movies.count())
				.from(movies, moviesGenre)
				.where(builder);

		System.out.println("countQuery = " + countQuery);
//		return new PageImpl<>(content, pageable, total);
		return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetch().size());
	}
}
