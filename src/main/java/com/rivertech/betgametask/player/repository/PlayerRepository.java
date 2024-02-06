package com.rivertech.betgametask.player.repository;

import java.util.List;
import com.rivertech.betgametask.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rivertech.betgametask.player.LeaderboardProjection;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByUserName(String userName);

    boolean existsByUserName(String userName);

    List<LeaderboardProjection> findAllByBetsNotEmpty();

}
