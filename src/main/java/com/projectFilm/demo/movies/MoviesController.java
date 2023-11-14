package com.projectFilm.demo.movies;

import com.projectFilm.demo.moviesGenre.QMoviesGenre;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
public class MoviesController {

	@Autowired
	private MoviesRepository moviesRepository;

	@Autowired
	EntityManager em;

	JPAQueryFactory queryFactory;


	QMovies m = QMovies.movies;
	QMoviesGenre mg = QMoviesGenre.moviesGenre;

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

//	@GetMapping("/movies/count/all")
//	public long ResponseAllCount(@RequestParam(required = false, defaultValue = "0") int genreId) {
//		try {
//			long result;
//			if (genreId == 0) {
//				result = moviesRepository.count();
//
//			} else {
//				result = moviesRepository.countMoviesByGenreNotContaining(genreId);
//
//			}
////			log.info(String.valueOf(result));
//			return result;
//		} catch (Exception e) {
//			log.error(e.toString());
//			return 0;
//		}
//	}

	@GetMapping("/movies/count/all")
	public long ResponseAllCount(
			@RequestParam(required = false, defaultValue = "") List<Integer> genreIds,
			@RequestParam(required = false, defaultValue = "") List<Short> prdtYears
	) {
		try {
			queryFactory = new JPAQueryFactory(em);

			List<Movies> result = queryFactory
					.select(m)
					.from(m)
					.leftJoin(m.moviesGenre, mg).on(mg.movieCd.eq(m.movieCd))
					.where(mg.genreId.notIn(genreIds))
					.where(m.prdtYear.notIn(prdtYears))
					.fetch();
			long count = result.size();
			System.out.println("count:" + count);
//			log.info(String.valueOf(result));
			return count;
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
