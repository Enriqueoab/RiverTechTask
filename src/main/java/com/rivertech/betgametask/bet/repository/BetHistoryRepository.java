package com.rivertech.betgametask.bet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.rivertech.betgametask.bet.BetHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetHistoryRepository  extends JpaRepository<BetHistory, Long> {
    Page<BetHistory> findAllByPlayerId(Long id, Pageable pageable);

    Page<BetHistory> findAllByPlayerIdAndGameResultNotNull(Long playerId, Pageable pageable);
}
