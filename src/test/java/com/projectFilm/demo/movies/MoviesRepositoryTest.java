package com.projectFilm.demo.movies;

import com.projectFilm.demo.movies.entity.Movies;
import com.projectFilm.demo.movies.repository.MoviesRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoviesRepositoryTest {

	@Autowired
	MoviesRepository moviesRepository;

	@Autowired
	EntityManager em;
	JPAQueryFactory queryFactory;
	QMovies m = QMovies.movies;

	@Test
	@Transactional
	public void MoviesTest() {

		List<Movies> moviesList = moviesRepository.findAll();

		Movies movies = moviesList.get(0);
		System.out.println(movies.getMovieCd());
		assertThat(movies.getMovieCd()).isEqualTo("19820019");
	}

	@Test
	public void MoviesDSLTest() {
		queryFactory = new JPAQueryFactory(em);

		Movies findMovie = queryFactory
				.select(m)
				.from(m)
				.where(m.genreAlt.notIn("멜로/로맨스", "다큐멘터리"))
				.fetchFirst();

		assertThat(Objects.requireNonNull(findMovie).getGenreAlt()).isNotEqualTo("멜로/로맨스");
		assertThat(Objects.requireNonNull(findMovie).getMovieNm()).isEqualTo("형");
	}
}
