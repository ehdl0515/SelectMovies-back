package com.projectFilm.demo.movies;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoviesRepositoryTest {

	@Autowired
	MoviesRepository moviesRepository;

	@Test
	@Transactional
	public void MoviesTest() {

		List<Movies> moviesList = moviesRepository.findAll();

		Movies movies = moviesList.get(0);
		System.out.println(movies.getMovieCd());
		assertThat(movies.getMovieCd()).isEqualTo("19820019");
	}
}
