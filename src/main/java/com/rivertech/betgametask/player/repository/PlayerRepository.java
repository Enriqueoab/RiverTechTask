package com.rivertech.betgametask.player.repository;

import com.rivertech.betgametask.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByUserName(String userName);

    boolean existsByUserName(String userName);

    Player findByWalletId(int walletId);

    List<Player> findAllByIdIn(List<Long> playerIds);
}
