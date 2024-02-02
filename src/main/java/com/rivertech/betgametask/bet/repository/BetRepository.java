package com.rivertech.betgametask.bet.repository;

import com.rivertech.betgametask.bet.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Integer> {
}
