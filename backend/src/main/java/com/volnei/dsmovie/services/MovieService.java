package com.volnei.dsmovie.services;

import com.volnei.dsmovie.dto.MovieDTO;
import com.volnei.dsmovie.entities.Movie;
import com.volnei.dsmovie.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

	private final MovieRepository repository;

	public MovieService(MovieRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public Page<MovieDTO> findAll(Pageable pageable) {
		Page<Movie> result = repository.findAll(pageable);
		return result.map(MovieDTO::new);
	}
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Movie result = repository.findById(id).get();
		return new MovieDTO(result);
	}
}