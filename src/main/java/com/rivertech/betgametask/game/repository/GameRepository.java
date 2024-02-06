package com.rivertech.betgametask.game.repository;

import com.rivertech.betgametask.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}
