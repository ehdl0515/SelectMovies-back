package com.projectFilm.demo.movies.controller;

import com.projectFilm.demo.movies.entity.Movies;
import com.projectFilm.demo.movies.entity.MoviesRequestDTO;
import com.projectFilm.demo.movies.service.MoviesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoviesControllerTest {

	@Autowired
	MoviesService moviesService;

	@Test
	@Transactional
	public void ResponseMoviesAllWithPageSortTest() {

		MoviesRequestDTO requestDTO = new MoviesRequestDTO();

		requestDTO.setPage(0);
		requestDTO.setSize(10);
		requestDTO.setSortColumn("genreAlt");
		requestDTO.setSortDesc(true);
		System.out.println("requestDTO = " + requestDTO);
		System.out.println("moviesService = " + moviesService);
		Page<Movies> result = moviesService.getAllMoviesWithPage(requestDTO);

		System.out.println("result = " + result);
		Movies firstContent = result.getContent().get(0);
		System.out.println("firstContent = " + firstContent);

		assertThat(firstContent.getMovieCd()).isEqualTo("20187861");
		System.out.println("firstContent.getMovieCd() = " + firstContent.getMovieCd());

		requestDTO.setSortDesc(false);

		Page<Movies> resultAsc = moviesService.getAllMoviesWithPage(requestDTO);

		Movies firstContentAsc = resultAsc.getContent().get(0);
		System.out.println("firstContentAsc = " + firstContentAsc);
		assertThat(firstContentAsc.getMovieCd()).isEqualTo("20001000");
		System.out.println("firstContentAsc.getMovieCd() = " + firstContentAsc.getMovieCd());

	}
}