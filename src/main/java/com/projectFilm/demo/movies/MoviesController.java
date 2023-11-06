package com.projectFilm.demo.movies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
	public Page<Movies> ResponseAllListWithPage(@RequestParam(required = false, defaultValue = "0") int page) {
		try {
			return moviesRepository.findAll(PageRequest.of(page, 10, Sort.by("openDt").descending()));
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}
	}

	@GetMapping("/movies/all")
	public long ResponseAllList() {
		try {
//			List<Movies> result = moviesRepository.findMoviesWithLimit(175);
			long result = moviesRepository.count();
//			log.info(String.valueOf(result));
			return result;
		} catch (Exception e) {
			log.error(e.toString());
			return 0;
		}
	}

	@PostMapping("/movies/one")
	public Object PostOneList(@RequestBody int movieCd) {
		try {
			Optional<Movies> result = moviesRepository.findById(String.valueOf(movieCd));
			log.info(String.valueOf(result));
			return result;

		} catch (Exception e) {
			log.error(e.toString());
			return Optional.empty();

		}

	}

}
