package com.volnei.dsmovie.repositories;

import com.volnei.dsmovie.entities.Score;
import com.volnei.dsmovie.entities.ScorePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, ScorePK> {

}