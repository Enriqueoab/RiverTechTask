package com.rivertech.betgametask.bet.service;

import java.util.List;
import com.rivertech.betgametask.bet.Bet;
import org.springframework.data.domain.Page;
import com.rivertech.betgametask.bet.BetHistory;
import org.springframework.data.domain.Pageable;
import com.rivertech.betgametask.bet.BetHistoryForm;
import com.rivertech.betgametask.utils.exception.NotFoundException;
public interface BetHistoryService {

    void generateBetHistoryRecords(List<Bet> bets);

    Page<BetHistory> retrieveBetResults(BetHistoryForm BetHisForm, Pageable pageable) throws NotFoundException;
}
