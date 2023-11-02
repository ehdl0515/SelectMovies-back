package com.projectFilm.demo.movies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
	public Object PostOneList(@RequestBody int movieCd) {
		try {
			Optional<Movies> result = moviesRepository.findById(String.valueOf(movieCd));
			System.out.println(result);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");

			// ResponseEntity로 응답 생성
			ResponseEntity<Optional<Movies>> rtn = ResponseEntity.ok()
					.headers(headers)
					.body(result);
			System.out.println(rtn);
			return rtn;

		} catch (Exception e) {
//			log.error(e.toString());
			System.out.println(e.toString());
			return Optional.empty();

		}

	}

}
