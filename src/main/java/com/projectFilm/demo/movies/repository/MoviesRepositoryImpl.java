package com.projectFilm.demo.movies.repository;

import com.projectFilm.demo.movies.entity.Movies;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
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

	public List<Movies> search(MoviesSearchCondition condition) {

		BooleanBuilder whereClause = buildWhereClause(condition);

		return jpaQueryFactory
				.select(movies)
				.from(movies, moviesGenre)
				.where(whereClause)
				.fetch();
	}


	public Page<Movies> searchPage(MoviesSearchCondition condition, Pageable pageable) {

		BooleanBuilder whereClause = buildWhereClause(condition);

		System.out.println("pageable = " + pageable);
		List<Movies> content = jpaQueryFactory
				.select(movies)
				.from(movies, moviesGenre)
				.where(whereClause)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		System.out.println("content = " + content);
		JPAQuery<Long> countQuery = jpaQueryFactory
				.select(movies.count())
				.from(movies, moviesGenre)
				.where(whereClause);

		System.out.println("countQuery = " + countQuery);
		return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetch().size());
	}


	private BooleanBuilder buildWhereClause(MoviesSearchCondition condition) {
		BooleanBuilder whereClause = new BooleanBuilder();

		whereClause.and(movies.movieCd.eq(moviesGenre.movieCd));

		if (condition.getPrdtYearLoe() != null) {
			whereClause.and(movies.openDt.loe(condition.getOpenDtLoe()));
		}
		if (condition.getPrdtYearGoe() != null) {
			whereClause.and(movies.openDt.goe(condition.getOpenDtGoe()));
		}

		if (condition.getOpenDtGoe() != null) {
			whereClause.and(movies.openDt.goe(condition.getOpenDtGoe()));
		}
		if (condition.getOpenDtLoe() != null) {
			whereClause.and(movies.openDt.loe(condition.getOpenDtLoe()));
		}

		if (condition.getTypeNms() != null && !condition.getTypeNms().isEmpty()) {
			whereClause.and(movies.typeNm.in(condition.getTypeNms()));
		}

		if (condition.getPrdtStatNms() != null && !condition.getPrdtStatNms().isEmpty()) {
			whereClause.and(movies.prdtStatNm.in(condition.getPrdtStatNms()));
		}

		if (condition.getRepNationNms() != null && !condition.getRepNationNms().isEmpty()) {
			whereClause.and(movies.repGenreNm.in(condition.getRepNationNms()));
		}

		if (condition.getGenreIds() != null && !condition.getGenreIds().isEmpty()) {
			whereClause.and(moviesGenre.genreId.in(condition.getGenreIds()));
		}

		return whereClause;
	}
}
