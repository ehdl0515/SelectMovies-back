package com.projectFilm.demo.movies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public Page<Movies> ResponseAllListWithPage(
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "0") int genreId) {
		try {
			Page<Movies> result;

			if (genreId == 0) {
				result = moviesRepository.findAll(PageRequest.of(page, 10, Sort.by("openDt").descending()));
			} else {
				Pageable pageable = PageRequest.of(page, 10, Sort.by("openDt").descending());
				result = moviesRepository.findMoviesByGenreNotContainingPage(genreId, pageable);
			}
			return result;
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}
	}

	@GetMapping("/movies/count/all")
	public long ResponseAllCount(@RequestParam(required = false, defaultValue = "0") int genreId) {
		try {
			long result;
			if (genreId == 0) {
				result = moviesRepository.count();

			} else {
				result = moviesRepository.countMoviesByGenreNotContaining(genreId);

			}
//			log.info(String.valueOf(result));
			return result;
		} catch (Exception e) {
			log.error(e.toString());
			return 0;
		}
	}

	@GetMapping("/movies/all")
	public List<Movies> ResponseAllData(@RequestParam(required = false, defaultValue = "0") int genreId) {
		try {
			List<Movies> result;
			if (genreId == 0) {
				result = moviesRepository.findAll();
			} else {
				result = moviesRepository.findMoviesByGenreNotContaining(genreId);
			}
//			log.info(String.valueOf(result));
			return result;
		} catch (Exception e) {
			log.error(e.toString());
			return null;
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
