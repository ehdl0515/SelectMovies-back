package com.projectFilm.demo.movies.service;

import com.projectFilm.demo.movies.entity.DailyBoxOffice;
import com.projectFilm.demo.movies.entity.Movies;
import com.projectFilm.demo.movies.entity.MoviesRequestDTO;
import com.projectFilm.demo.movies.repository.MoviesRepository;
import com.projectFilm.demo.movies.repository.MoviesSearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoviesService {

	private final MoviesRepository moviesRepository;

	public List<Movies> getAllMovies(MoviesRequestDTO requestDTO) {

		MoviesSearchCondition condition = createMoviesSearchCondition(requestDTO);
		log.info("condition = " + condition);

		return moviesRepository.search(condition);
	}

	public Page<Movies> getAllMoviesWithPage(MoviesRequestDTO requestDTO) {

		MoviesSearchCondition condition = createMoviesSearchCondition(requestDTO);
		log.info("condition = " + condition);

		Pageable pageable = createPageable(requestDTO);
		log.info("pageable = " + pageable);

		return moviesRepository.searchPage(condition, pageable);
	}

	public List<DailyBoxOffice> getDailyRank(int dateStamp) {
		return moviesRepository.searchDailyRank(dateStamp);
	}


	public MoviesSearchCondition createMoviesSearchCondition(
			MoviesRequestDTO requestDTO) {

		MoviesSearchCondition condition = new MoviesSearchCondition();
		condition.setPrdtYearGoe(requestDTO.getPrdtYearGoe());
		condition.setPrdtYearLoe(requestDTO.getPrdtYearLoe());
		condition.setOpenDtGoe(requestDTO.getOpenDtGoe());
		condition.setOpenDtLoe(requestDTO.getOpenDtLoe());
		condition.setTypeNms(requestDTO.getTypeNms());
		condition.setPrdtStatNms(requestDTO.getPrdtStatNms());
		condition.setRepNationNms(requestDTO.getRepNationNms());
		condition.setGenreIds(requestDTO.getGenreIds());

		return condition;
	}

	public Pageable createPageable(MoviesRequestDTO requestDTO) {
		int page;
		int size;

		if (requestDTO.getPage() == null) {
			page = 0;
		} else {
			page = requestDTO.getPage();
		}

		if (requestDTO.getSize() == null) {
			size = 10;
		} else {
			size = requestDTO.getSize();
		}

		if (requestDTO.getSortColumn() == null) {
			return PageRequest.of(page, size);
		}

		Sort setSortCondition = Sort.by(requestDTO.getSortColumn()).descending();
		if (!requestDTO.getSortDesc())  {
			setSortCondition = Sort.by(requestDTO.getSortColumn()).ascending();
		}
		return PageRequest.of(page, size, setSortCondition);
	}
}
