package com.projectFilm.demo.movies.service;

import com.projectFilm.demo.movies.entity.Movies;
import com.projectFilm.demo.movies.entity.MoviesRequestDTO;
import com.projectFilm.demo.movies.repository.MoviesRepository;
import com.projectFilm.demo.movies.repository.MoviesSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoviesService {

	private final MoviesRepository moviesRepository;

	public List<Movies> getAllMovies(MoviesRequestDTO requestDTO) {

		MoviesSearchCondition condition = createMoviesSearchCondition(requestDTO);
		System.out.println("condition = " + condition);

		return moviesRepository.search(condition);
	}

	public Page<Movies> getAllMoviesWithPage(MoviesRequestDTO requestDTO) {

		MoviesSearchCondition condition = createMoviesSearchCondition(requestDTO);
		System.out.println("condition = " + condition);

		Pageable pageable = createPageable(requestDTO);
		System.out.println("pageable = " + pageable);

		return moviesRepository.searchPage(condition, pageable);
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
		Sort setSortCondition = Sort.by(requestDTO.getSortColumn()).descending();
		if (!requestDTO.getSortDesc())  {
			setSortCondition = Sort.by(requestDTO.getSortColumn()).ascending();
		}
		return PageRequest.of(requestDTO.getPage(), requestDTO.getSize(), setSortCondition);
	}
}
