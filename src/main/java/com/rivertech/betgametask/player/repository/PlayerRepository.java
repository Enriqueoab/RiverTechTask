package com.rivertech.betgametask.player.repository;

import com.rivertech.betgametask.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Player findByUserName(String userName);
}
