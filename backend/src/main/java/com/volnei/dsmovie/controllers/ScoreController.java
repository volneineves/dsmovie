package com.volnei.dsmovie.controllers;

import com.volnei.dsmovie.dto.MovieDTO;
import com.volnei.dsmovie.dto.ScoreDTO;
import com.volnei.dsmovie.services.ScoreService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/scores")
public class ScoreController {
	
	private final ScoreService service;

	public ScoreController(ScoreService service) {
		this.service = service;
	}

	@PutMapping
	public MovieDTO saveScore(@RequestBody ScoreDTO dto) {
		return (MovieDTO) service.saveScore(dto);
	}
}