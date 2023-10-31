package com.projectFilm.demo.movies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
public class MoviesController {

	@Autowired
	private MoviesRepository moviesRepository;

	@GetMapping("/movies")
	public List<Movies> ShowAllList() {
		try {
			return moviesRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}
}
