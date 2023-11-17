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
	public List<Movies> ResponseAllMovies(@ModelAttribute MoviesRequestDTO requestDTO) {
		try {
			log.info("Request to path = '/movies' with params: " + requestDTO);
			List<Movies> response = moviesService.getAllMovies(requestDTO);

			log.info("Response = " + response);
			return response;
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}
	}

	@GetMapping("movies/page")
	public Page<Movies> ResponseAllMoviesWithPage(@ModelAttribute MoviesRequestDTO requestDTO) {
		try {
			log.info("Request to path = '/movies/page' with params: " + requestDTO);

			Page<Movies> response = moviesService.getAllMoviesWithPage(requestDTO);

			log.info("Response = " + response);
			return response;
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}
	}
}
