package com.rivertech.betgametask.bet.service;

import java.util.List;
import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.player.Player;
import org.springframework.data.domain.Page;
import com.rivertech.betgametask.bet.BetHistory;
import org.springframework.data.domain.Pageable;

public interface BetHistoryService {

    List<BetHistory> generateBetHistoryRecord(Bet bet);

    Page<BetHistory> retrieveBetResults(Player player, boolean isJustExecutedBets, Pageable pageable);
}
