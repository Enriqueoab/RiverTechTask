package com.rivertech.betgametask.bet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.rivertech.betgametask.bet.BetHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetHistoryRepository  extends JpaRepository<BetHistory, Long> {
    Page<BetHistory> findAllByPlayerId(Long id, Pageable pageable);

    //@Query(value = "SELECT u FROM User AS u WHERE u.id = ?1")
    Page<BetHistory> findAllByPlayerIdAndGameResultNotNull(Long playerId, Pageable pageable);
}
