package com.projectFilm.demo.movies.controller;

import com.projectFilm.demo.movies.entity.Movies;
import com.projectFilm.demo.movies.entity.MoviesRequestDTO;
import com.projectFilm.demo.movies.service.MoviesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
//@RequestMapping("/movies")
public class MoviesController {

	private final MoviesService moviesService;

	@Autowired
	public MoviesController(MoviesService moviesService) {
		this.moviesService = moviesService;
	}

	@GetMapping("movies")
	public Object ResponseAllMovies(@ModelAttribute MoviesRequestDTO requestDTO) {
		try {
			System.out.println("requestDTO = " + requestDTO);
			List<Movies> result = moviesService.getAllMovies(requestDTO);

			System.out.println("result:" + result);
			log.info("result: " + result);
			return result;
		} catch (Exception e) {
			System.out.println("e:" + e);
			log.error(e.toString());
			return 0;
		}
	}

	@GetMapping("movies/page")
	public Object ResponseAllMoviesWithPage(@ModelAttribute MoviesRequestDTO requestDTO) {
		try {
			log.info("Request From User: url:'/movies/page' with params");

			Page<Movies> result = moviesService.getAllMoviesWithPage(requestDTO);

			System.out.println("result:" + result);
			log.info("result: " + result);
			return result;
		} catch (Exception e) {
			System.out.println("e:" + e);
			log.error(e.toString());
			return 0;
		}
	}
}
