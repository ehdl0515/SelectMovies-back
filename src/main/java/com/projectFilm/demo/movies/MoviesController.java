package com.projectFilm.demo.movies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
public class MoviesController {

	@Autowired
	private MoviesRepository moviesRepository;

	@GetMapping("/movies")
	public List<Movies> ResponseAllList() {
		try {
			return moviesRepository.findAll();
		} catch (Exception e) {
			log.error(e.toString());
			return null;

		}

	}
	@PostMapping("/movies/one")
	public Optional<Movies> PostOneList(@RequestBody int movieCd) {
		try {
			return moviesRepository.findById(String.valueOf(movieCd));
		} catch (Exception e) {
			log.error(e.toString());
			return Optional.empty();

		}

	}

}
