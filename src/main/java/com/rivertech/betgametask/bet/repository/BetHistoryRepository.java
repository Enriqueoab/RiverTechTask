package com.rivertech.betgametask.bet.repository;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.bet.BetHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetHistoryRepository  extends JpaRepository<BetHistory, Long> {
}
