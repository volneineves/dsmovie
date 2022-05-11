package com.volnei.dsmovie.services;

import com.volnei.dsmovie.dto.MovieDTO;
import com.volnei.dsmovie.dto.ScoreDTO;
import com.volnei.dsmovie.entities.Movie;
import com.volnei.dsmovie.entities.Score;
import com.volnei.dsmovie.entities.User;
import com.volnei.dsmovie.repositories.MovieRepository;
import com.volnei.dsmovie.repositories.ScoreRepository;
import com.volnei.dsmovie.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {

	private final MovieRepository movieRepository;
	private final UserRepository userRepository;
	private final ScoreRepository scoreRepository;

	public ScoreService(MovieRepository movieRepository, UserRepository userRepository, ScoreRepository scoreRepository) {
		this.movieRepository = movieRepository;
		this.userRepository = userRepository;
		this.scoreRepository = scoreRepository;
	}

	@Transactional
	public MovieDTO saveScore(ScoreDTO dto) {
		
		User user = userRepository.findByEmail(dto.getEmail());
		if (user == null) {
			user = new User();
			user.setEmail(dto.getEmail());
			user = userRepository.saveAndFlush(user);
		}
		
		Movie movie = movieRepository.findById(dto.getMovieId()).get();
		
		Score score = new Score();
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(dto.getScore());
		
		score = scoreRepository.saveAndFlush(score);
		
		double sum = 0.0;

		for (Score s : movie.getScores()) {
			sum = sum + s.getValue();
		}
			
		double avg = sum / movie.getScores().size();
		
		movie.setScore(avg);
		movie.setCount(movie.getScores().size());
		
		movie = movieRepository.save(movie);
		
		return new MovieDTO(movie);
	}
}